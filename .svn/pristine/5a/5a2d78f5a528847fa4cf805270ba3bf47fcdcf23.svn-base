package com.sanmina.tk.web;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.sanmina.skid.pdf.BuildPdfSkid;
import com.sanmina.tk.orm.dao.skid.ContainerDao;
import com.sanmina.tk.orm.dao.skid.PartNumberDao;
import com.sanmina.tk.orm.dao.skid.SerialDao;
import com.sanmina.tk.orm.model.skid.Container;
import com.sanmina.tk.orm.model.skid.PartNumber;
import com.sanmina.tk.orm.model.skid.Serial;
import com.sanmina.tk.orm.model.skid.tbl_skid;

@Controller
public class RequestDispatcherSkid {

	@Autowired
	private PartNumberDao partNumberDao;

	@Autowired
	private ContainerDao containerDao;

	@Autowired
	private SerialDao serialDao;

	private List<tbl_skid> skidList = new ArrayList<tbl_skid>();
	private String partNumber = "";
	String revision = "";

	@RequestMapping(value = "ConsulSerial", method = RequestMethod.POST)
	public @ResponseBody ModelAndView ConsulSerial(ModelMap model, @RequestParam("serial") String serial)
			throws IOException, JSONException, ParseException {
		try {
			String url = "http://localhost:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
					+ serial.replace(" ", "%20");
			JSONObject jsonSerial = jsonView(url);
			if (revision == "")
				revision = jsonSerial.getString("revision");
			JSONObject jsonPart = (JSONObject) jsonSerial.get("partNumber");

			tbl_skid skid = new tbl_skid();

			skid.setSerial(jsonSerial.getString("serial"));

			PartNumber part = partNumberDao.findPartByPart(jsonPart.getString("partNumber"));
			skid.setPartNumber(part.getPart_Print());
			skid.setRevision(revision);
			skid.setPo("po");
			Serial tempSerial = serialDao.findSerialBySerial(skid.getSerial());
			if(tempSerial == null)
				skidList.add(skid);
			else
				return null;

			model.addAttribute("skidList", skidList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("skid/TableDivSkid", model);
	}

	@RequestMapping(value = "BuildPDF")
	public ResponseEntity<byte[]> BuildPDF(Long contenedor) {
		byte[] contents;
		ResponseEntity<byte[]> response = null;
		try {
			String path = "ticketC.pdf";
			File file = new File(path);
			BuildPdfSkid doc = new BuildPdfSkid(contenedor.toString(), revision, skidList);
			Document document = new Document();
			System.out.println("Document created...");
			try {
				PdfWriter.getInstance(document, new FileOutputStream(file));
				System.out.println("Writer instance created...");
				document.open();
				System.out.println("Document open...");
				doc.buildDoc(document);
				System.out.println("Final format to document...");
			} catch (Exception e) {
				e.printStackTrace();
			}
			document.close();
			System.out.println("Document closed...");
			contents = loadFile(path);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			String filename = "Skid.pdf";
			headers.setContentDispositionFormData(filename, filename);
			headers.setExpires(0);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			headers.setPragma("public");
			response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
			PartNumber part = partNumberDao.findPartByPart(partNumber);
			long idPart = part.getPart_ID();

			Container container = containerDao.findContainerByContainer(contenedor);
			container.setConte_status(1);
			container.setConte_acum(skidList.size());
			container.setConte_print(1);
			containerDao.updateContainer(container);

			for (int i = 0; i < skidList.size(); i++) {
				Serial newSerial = new Serial();
				Serial tempSerial = serialDao.findSerialBySerial(skidList.get(i).getSerial());
				if (tempSerial == null) {
					System.out.println("El serial es nullo");

					newSerial.setConte_id(container.getConte_id());
					System.out.println("El setConte_id es nullo" + container.getConte_id());
					newSerial.setSerial_number(skidList.get(i).getSerial());
					System.out.println("El setSerial_number es nullo" + skidList.get(i).getSerial());
					newSerial.setSerial_rev(revision);
					System.out.println("El setSerial_rev es nullo" + revision);
					newSerial.setPart_ID(idPart);
					System.out.println("El setPart_ID es nullo" + idPart);
					serialDao.saveSerial(newSerial);
				} else
					System.out.print("El serial ya existe en la base de datos");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}

	@RequestMapping(value = "searchInfo")
	public @ResponseBody String SearchInfo(String serial) {
		String Part_qty_container = "";
		try {
			if (serial != "") {
				String url = "http://localhost:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
						+ serial.replace(" ", "%20");
				JSONObject jsonSerial = jsonView(url);
				JSONObject jsonPart = (JSONObject) jsonSerial.get("partNumber");
				PartNumber partNumber = partNumberDao.findPartByPart(jsonPart.getString("partNumber"));
				if (partNumber != null)
					Part_qty_container = partNumber.getPart_qty_container().toString();
				else
					return "Error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Part_qty_container;
	}

	@RequestMapping(value = "newContainer")
	public @ResponseBody String NewContainer(String proyecto, Principal principal) {
		System.out.println(proyecto);
		Container container = new Container();
		try {
			int idProject = 0;
			container.setConte_status(1);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String formattedDate = formatter.format(date);
			container.setConte_date(formattedDate);
			if (proyecto.equals("MCE-PCA"))
				idProject = 15;
			else if (proyecto.equals("MCE-BOX"))
				idProject = 16;
			else if (proyecto.equals("GENBAND"))
				idProject = 17;
			if (idProject != 0)
				container.setProyecto_id(idProject);
			containerDao.saveContainer(container);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return container.getConte_id().toString();
	}

	@RequestMapping(value = "ClearList")
	public @ResponseBody ModelAndView ClearList(ModelMap model) {
		try {
			skidList.clear();
			partNumber = "";
			revision="";
			model.addAttribute("skidList", skidList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("skid/TableDivSkid", model);
	}

	@RequestMapping(value = "validateComp", method = RequestMethod.POST)
	public @ResponseBody Boolean ValidateComp(@RequestParam("serial") String serial)
			throws IOException, JSONException, ParseException {
		Boolean estatus = false;
		try {
			String url = "http://localhost:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
					+ serial.replace(" ", "%20");
			JSONObject jsonSerial = jsonView(url);
			JSONObject jsonPart = (JSONObject) jsonSerial.get("partNumber");
			JSONObject jsonLocation = (JSONObject) jsonSerial.get("location");
			if (jsonLocation.getString("shortWorkStation").equals("COMP"))
				estatus = true;
			int count = 0;
			for (int i = 0; i < skidList.size(); i++)
				if (jsonSerial.getString("serial").equals(skidList.get(i).getSerial()))
					count = count + 1;
			if (count > 0)
				return false;

			if (partNumber == "")
				partNumber = jsonPart.getString("partNumber");
			if (!jsonPart.getString("partNumber").equals(partNumber))
				estatus = false;
			else
				estatus = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return estatus;
	}

	@RequestMapping(value = "size")
	public @ResponseBody Integer size() {
		Integer lista = 0;
		try {
			lista = skidList.size();
		} catch (Exception e) {

		}
		return lista;
	}

	public JSONObject jsonView(String url) throws IOException {
		JSONObject jsonobject = null;
		try {
			URL TempUrl = new URL(url);
			URLConnection urlConnection = TempUrl.openConnection();
			urlConnection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			JSONArray jsonarray = null;
			while ((inputLine = br.readLine()) != null)
				jsonarray = new JSONArray("[" + inputLine + "]");
			br.close();
			if (jsonarray.length() != 0)
				for (int i = 0; i < jsonarray.length(); i++)
					jsonobject = jsonarray.getJSONObject(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonobject;
	}

	public static byte[] readFully(InputStream stream) throws IOException {
		byte[] buffer = new byte[8192];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int bytesRead;
		while ((bytesRead = stream.read(buffer)) != -1) {
			baos.write(buffer, 0, bytesRead);
		}
		return baos.toByteArray();
	}

	public static byte[] loadFile(String sourcePath) throws IOException {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(sourcePath);
			return readFully(inputStream);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}
}
