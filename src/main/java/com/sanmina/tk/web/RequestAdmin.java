package com.sanmina.tk.web;

//import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//import com.sanmina.tk.csv.ManagerCSV;
import com.sanmina.tk.orm.dao.FileLogDao;
import com.sanmina.tk.orm.dao.PoDao;
import com.sanmina.tk.orm.dao.PoLineDao;
import com.sanmina.tk.orm.dao.ShippingInstructionDao;
import com.sanmina.tk.orm.dao.tblInstructionReportDao;
import com.sanmina.tk.orm.model.FileLog;
import com.sanmina.tk.orm.model.Po;
import com.sanmina.tk.orm.model.PoLine;
import com.sanmina.tk.orm.model.ShippingInstruction;
import com.sanmina.tk.orm.model.tblInstructionReport;

@Controller
public class RequestAdmin {
	@Autowired
	private tblInstructionReportDao instructionReportDao;

	@Autowired
	private ShippingInstructionDao instructionDao;

	@Autowired
	private PoLineDao poLineDao;

	@Autowired
	private FileLogDao fileLogDao;

	@Autowired
	private PoDao poDao;

	@RequestMapping(value = "FileManager")
	public ModelAndView landingResolv(ModelMap model) {
		return new ModelAndView("Administration/FileManager", model);
	}

	@RequestMapping(value = "loadAllInstructionReport")
	public @ResponseBody ModelAndView LoadAllInstructions(ModelMap model) {
		try {
			List<tblInstructionReport> reportList = instructionReportDao.findAllInstructionReport();
			for (int i = 0; i < reportList.size(); i++) {
				System.out.println("Estas son las instrucciones: " + reportList.get(i).getShippingInstruction());
				if (reportList.get(i).getActive() == "0") {
					reportList.get(i).setActive("false");
				} else {
					reportList.get(i).setActive("true");
				}
			}
			model.addAttribute("reportList", reportList);
		} catch (Exception e) {
		}
		return new ModelAndView("Administration/TableDivInstruction", model);
	}

	@RequestMapping(value = "/uploadOracleFile", headers = "content-type=multipart/*", method = RequestMethod.POST)
	public @ResponseBody String uploadOracle(@RequestParam(value = "file") MultipartFile excelFile, ModelMap model,
			HttpServletResponse response, HttpServletRequest request) {
		String vMsg = "";
//		List<PoLineExisting> poLineExisting = new ArrayList<>();
		try {
			if (excelFile != null) {
				// List<AP> rows = new ArrayList<>();
				XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
				System.out.println("Reading the tirsh book...");
				XSSFSheet worksheet = workbook.getSheetAt(0);
				XSSFRow row;
				DataFormatter df = new DataFormatter();
				tblInstructionReport instruction;
				int count = 0;
				List<tblInstructionReport> badInstruction = new ArrayList<tblInstructionReport>();
				List<tblInstructionReport> instructionPass = new ArrayList<tblInstructionReport>();
				List<FileLog> newFiles = new ArrayList<FileLog>();
				int lastRow = worksheet.getLastRowNum();
				System.out.println("Prueba de ver los registros fisicos " + lastRow);

				// ----------For que recore para saber que rows tienen
				// contenido------------------
				for (int i = 1; i <= lastRow; i++) {
					System.out.println("Reading row:" + i + "/" + lastRow + ".");
					row = worksheet.getRow(i);
					try {
						if (df.formatCellValue(row.getCell(0)) != "" || df.formatCellValue(row.getCell(1)) != ""
								|| df.formatCellValue(row.getCell(2)) != "" || df.formatCellValue(row.getCell(3)) != ""
								|| df.formatCellValue(row.getCell(4)) != "" || df.formatCellValue(row.getCell(5)) != ""
								|| df.formatCellValue(row.getCell(6)) != "" || df.formatCellValue(row.getCell(7)) != "") {
							System.out.println("entro al if");
							count++;
						}
						// Po tempPo = poDao.findPoByPo(po);
						// if(tempPo == null){
						//
						// }else{
						//
						// }
						//
						//
						// PoLine tempPoline =
						// poLineDao.findPoLineByPoLine(poLine);
						// if (tempPoline == null) {
						// tempPoline.setActive(true);
						// tempPoline.setPartNumber(partNumber);
						// tempPoline.setPartQtyRequired(qtyRequired);
						// tempPoline.setPartQtyStored(0);
						// tempPoline.setPoLine(poLine);
						// poLineDao.savePoLine(tempPoline);
						// } else {
						// vMsg = "Esta PoLine ya existe y no se debe
						// actualizar";
						// }
						//

					} catch (NullPointerException e) {
						e.printStackTrace();
					}
				}

				for (int x = 1; x <= count; x++) {
					row = worksheet.getRow(x);
					String priority = df.formatCellValue(row.getCell(0));
					String shippInst = df.formatCellValue(row.getCell(1));
					String po = df.formatCellValue(row.getCell(2));
					String poLine = df.formatCellValue(row.getCell(3));
					String partNumber = df.formatCellValue(row.getCell(4));
					String qtyRequired = df.formatCellValue(row.getCell(5));
					String date = df.formatCellValue(row.getCell(6));
					String project = df.formatCellValue(row.getCell(7));
					instruction = new tblInstructionReport(priority, shippInst, po, poLine, partNumber, qtyRequired, 0,
							"Abierta", date, project);
					System.out.println("Este es un row del excel " + instruction.getShippingInstruction()
							+ " En la posicion " + x);

					if (instruction.getPriority() == "") {
						vMsg = vMsg + " La celda 0 en el row " + x + " se encuentra vacia.";
						System.out.println("Se agrego una instruccion mal");
						badInstruction.add(instruction);
					}

					if (instruction.getShippingInstruction() == "") {
						vMsg = vMsg + " La celda 1 en el row " + x + " se encuentra vacia.";
						System.out.println("Se agrego una instruccion mal");
						badInstruction.add(instruction);
					}

					if (instruction.getPo() == "") {
						vMsg = vMsg + " La celda 2 en el row " + x + " se encuentra vacia.";
						System.out.println("Se agrego una instruccion mal");
						badInstruction.add(instruction);
					}

					if (instruction.getPoLine() == "") {
						vMsg = vMsg + " La celda 3 en el row " + x + " se encuentra vacia.";
						System.out.println("Se agrego una instruccion mal");
						badInstruction.add(instruction);
					}

					if (instruction.getPartNumber() == "") {
						vMsg = vMsg + " La celda 4 en el row " + x + " se encuentra vacia.";
						System.out.println("Se agrego una instruccion mal");
						badInstruction.add(instruction);
					}

					if (instruction.getPartQtyRequired() == "") {
						vMsg = vMsg + " La celda 5 en el row " + x + " se encuentra vacia.";
						System.out.println("Se agrego una instruccion mal");
						badInstruction.add(instruction);
					}

					if (instruction.getExpiredDate() == "") {
						vMsg = vMsg + " La celda 6 en el row " + x + " se encuentra vacia.";
						System.out.println("Se agrego una instruccion mal");
						badInstruction.add(instruction);
					}

					if (instruction.getProject() == "") {
						vMsg = vMsg + " La celda 7 en el row " + x + " se encuentra vacia.";
						System.out.println("Se agrego una instruccion mal");
						badInstruction.add(instruction);
					}

					System.out.println("Variable de mensaje " + vMsg);
					if (vMsg == "") {
						System.out.println("Se agrega la instruccion a la lista pass!");
						instructionPass.add(instruction);
					}
				}
				workbook.close();
				if (badInstruction.size() <= 0) {
					vMsg = "Ok";
					for (int i = 0; i < instructionPass.size(); i++) {
						String stateLog = "";

						
						ShippingInstruction tempShipping = instructionDao
								.findInstruccionByShippingInstruction(instructionPass.get(i).getShippingInstruction());
						if (tempShipping == null) {
							tempShipping = new ShippingInstruction();
							SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
							tempShipping.setActive(true);
							tempShipping.setExpiredDate(date.parse(instructionPass.get(i).getExpiredDate()));
							tempShipping.setPriority(Integer.parseInt(instructionPass.get(i).getPriority()));
							tempShipping.setShippingInstruction(instructionPass.get(i).getShippingInstruction());
							tempShipping.setStartDate(new Date());
							tempShipping.setQtyPo(0);
							tempShipping.setProject(instructionPass.get(i).getProject());
							instructionDao.saveShippingInstruction(tempShipping);
						} else {
							stateLog = "Ok";
							SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
							tempShipping.setExpiredDate(date.parse(instructionPass.get(i).getExpiredDate()));
							tempShipping.setPriority(Integer.parseInt(instructionPass.get(i).getPriority()));
							tempShipping.setShippingInstruction(instructionPass.get(i).getShippingInstruction());
							instructionDao.updateShippingInstruction(tempShipping);

						}

						// Creacion de registro de la Po
						Po tempPo;
						// if (tempPo == null) {
						tempPo = new Po();
						ShippingInstruction inst = instructionDao
								.findInstruccionByShippingInstruction(instructionPass.get(i).getShippingInstruction());
						tempPo.setActive(true);
						tempPo.setPo(instructionPass.get(i).getPo());
						tempPo.setStartDate(new Date());
						tempPo.setIdShippingInstruction(inst.getIdShippingInstruction());
						inst.setQtyPo(inst.getQtyPo() + 1);
						poDao.savePo(tempPo);
						instructionDao.updateShippingInstruction(inst);
						// }else{
						// System.out.println("La PO ya existe en la base de
						// datos");
						// }

						// Creacion de registro de la PoLine
						PoLine tempPoline = poLineDao.findPoLineByPoLine(instructionPass.get(i).getPoLine());
						if (tempPoline == null) {
							tempPoline = new PoLine();
							tempPoline.setActive(true);
							tempPoline.setPartNumber(instructionPass.get(i).getPartNumber());
							tempPoline.setPartQtyRequired(Integer.parseInt(instructionPass.get(i).getPartQtyRequired()));
							tempPoline.setPartQtyStored(0);
							tempPoline.setPoLine(instructionPass.get(i).getPoLine());
							tempPoline.setIdPo(tempPo.getIdPo());
							poLineDao.savePoLine(tempPoline);
						} 
//						else {
//							PoLineExisting poline = new PoLineExisting(tempPoline.getPoLine(), tempPoline.getPartNumber());
//							poLineExisting.add(poline);
//						}

						if (stateLog == "Ok") {
							System.out.println("Agregamos la info al file log");
							System.out.println(tempShipping.getIdShippingInstruction());
							System.out.println(tempPo.getIdPo());
							FileLog fileLog = new FileLog();
							fileLog.setCreationDate(tempShipping.getStartDate());
							fileLog.setShippingInstruction(tempShipping.getShippingInstruction());
							fileLog.setUserName(request.getSession().getAttribute("LOGGEDIN_USER").toString());
							fileLog.setUpdateDate(new Date());
							fileLog.setPo(tempPo.getPo());
							fileLog.setPoLine(tempPoline.getPoLine());
							fileLog.setPartNumber(tempPoline.getPartNumber());
							newFiles.add(fileLog);
						}
					}
					// Validamos la lista de los filelog para agregar los nuevos
					// registros
					if (newFiles.size() > 0) {
						for (int i = 0; i < newFiles.size(); i++) {
							FileLog fileLog = new FileLog();
							fileLog.setCreationDate(newFiles.get(i).getCreationDate());
							fileLog.setShippingInstruction(newFiles.get(i).getShippingInstruction());
							fileLog.setUserName(newFiles.get(i).getUserName());
							fileLog.setUpdateDate(newFiles.get(i).getUpdateDate());
							fileLog.setPo(newFiles.get(i).getPo());
							fileLog.setPoLine(newFiles.get(i).getPoLine());
							fileLog.setPartNumber(newFiles.get(i).getPartNumber());
							System.out.println(
									"Creamos el nuevo file log de la instruccion " + fileLog.getShippingInstruction());
							fileLogDao.saveFileLog(fileLog);
							System.out.println(
									"Guardamos el file log de la instruccion " + fileLog.getShippingInstruction());
						}
					}
				}
			} else {
				vMsg = "excelFile is NULL";
			}
			
//			if(!poLineExisting.isEmpty()){
//				System.out.println("La lista de polines existentes no esta vacia");
//				InputStream is = ManagerCSV.getFile(poLineExisting);
//				// copy it to response's OutputStream
//				response.setContentType("application/vnd.ms-excel");
//				response.setHeader("Content-Disposition", "attachment; filename=PoLineExisting.xls");
//				IOUtils.copy(is, response.getOutputStream());
//				response.flushBuffer();
//				is.close();
//			}
		} catch (Exception e) {
			e.printStackTrace();
			vMsg = "Error al leer el archivo. Verifique que sea un archivo valido." + e.getMessage();
		}
		return vMsg;
	}
}
