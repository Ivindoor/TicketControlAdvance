package com.sanmina.tk.zpl;

import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sanmina.tk.orm.dao.ZPLDao;
import com.sanmina.tk.orm.model.Container;
import com.sanmina.tk.orm.model.ZPL;

public class PrintZPL {

	ZPLDao zpldao;

	public PrintZPL(ZPLDao zpldao) {
		this.zpldao = zpldao;
	}

	public String printIUSA(String ticket, long idProject, String partNumber, Long qty, String description) {
		String vMsg = "";
		try {
			String ZPLCode = "";
			ZPL zpl = zpldao.findZPLByIdProject(idProject);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			Date date = new Date();
			String formattedDate = formatter.format(date);
			ZPLCode = zpl.getZpl();
			ZPLCode = ZPLCode.replace("$DATE$", formattedDate);
			if (partNumber.contains("-RM")) {
				ZPLCode = ZPLCode.replace("$RMA$", "RMA");
			} else {
				ZPLCode = ZPLCode.replace("$RMA$", "");
			}
			ZPLCode = ZPLCode.replace("$TK$", ticket);
			ZPLCode = ZPLCode.replace("$DES$", description);
			ZPLCode = ZPLCode.replace("$PN$", partNumber);
			ZPLCode = ZPLCode.replace("$QTY$", qty.toString());
			vMsg = printZpl(ZPLCode, zpl.getIp(), 9100);
		} catch (Exception e) {
			e.printStackTrace();
			vMsg = e.getMessage();
		}
		return vMsg;
	}

	public String printHAAS(String ticket, List<Container> containers, long idProject, String project, String modelDescription, String partNumber) {
		String vMsg = "";
		try {
			String ZPLCode = "";
			ZPL zpl = zpldao.findZPLByIdProject(idProject);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			String formattedDate = formatter.format(date);
			int qtyContainer = 0;
			for (int i = 0; i < containers.size(); i++)
				qtyContainer += containers.get(i).getQty();
			ZPLCode = zpl.getZpl();
			ZPLCode = ZPLCode.replace("$DATE$", formattedDate);
			ZPLCode = ZPLCode.replace("$PROJECT$", project);
			ZPLCode = ZPLCode.replace("$TK$", ticket);
			ZPLCode = ZPLCode.replace("$DES$", modelDescription);
			ZPLCode = ZPLCode.replace("$PN$", partNumber);
			ZPLCode = ZPLCode.replace("$QTY$", Integer.toString(qtyContainer));
			if (containers.get(0).getPartNumber().contains("-RM"))
				ZPLCode = ZPLCode.replace("$RMA$", "RMA");
			else
				ZPLCode = ZPLCode.replace("$RMA$", "");
			vMsg = printZpl(ZPLCode, zpl.getIp(), 9100);
		} catch (Exception e) {
			vMsg = "Error: " + e.getMessage();
			e.printStackTrace();
		}
		return vMsg;
	}
	
	public String printNOKIAFSM4(String ticket, String partNumber, String revision, int qty, long idProject) {
		String vMsg = "";
		try {
			String ZPLCode = "";
			ZPL zpl = zpldao.findZPLByIdProject(idProject);
			ZPLCode = zpl.getZpl();
			ZPLCode = ZPLCode.replace("$REV$", revision);
			ZPLCode = ZPLCode.replace("$TICKET$", ticket);
			ZPLCode = ZPLCode.replace("$Part Number$", partNumber);
			ZPLCode = ZPLCode.replace("$QTY$", Integer.toString(qty));
			vMsg = printZpl(ZPLCode, zpl.getIp(), 9100);
		} catch (Exception e) {
			e.printStackTrace();
			vMsg = e.getMessage();
		}
		return vMsg;
	}

	public String printNOKIARRH(String ticket, List<Container> containers, long idProject, String project,
			String revision, String partNumber) {
		String vMsg = "";
		try {
			String ZPLCode = "";
			ZPL zpl = zpldao.findZPLByIdProject(idProject);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			Date date = new Date();
			String formattedDate = formatter.format(date);
			ZPLCode = zpl.getZpl();
			ZPLCode = ZPLCode.replace("$Date$", formattedDate);
			ZPLCode = ZPLCode.replace("$REV$", revision);
			ZPLCode = ZPLCode.replace("$Ticket$", ticket);
			ZPLCode = ZPLCode.replace("$pn_tl1$", containers.get(0).getPartNumber());
			ZPLCode = ZPLCode.replace("$QTY$", Integer.toString(containers.size()));
			vMsg = printZpl(ZPLCode, zpl.getIp(), 9100);
		} catch (Exception e) {
			e.printStackTrace();
			vMsg = e.getMessage();
		}
		return vMsg;
	}

	public String printDigital(String ticket, List<Container> containers, long idProject, String project,
			String modelDescription) {
		String vMsg = "";
		try {
			String ZPLCode = "";
			ZPL zpl = zpldao.findZPLByIdProject(idProject);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			Date date = new Date();
			String formattedDate = formatter.format(date);
			int qtyContainer = 0;
			for (int i = 0; i < containers.size(); i++)
				qtyContainer += containers.get(i).getQty();
			ZPLCode = zpl.getZpl();
			ZPLCode = ZPLCode.replace("$DATE$", formattedDate);
			ZPLCode = ZPLCode.replace("$TK$", ticket);
			ZPLCode = ZPLCode.replace("$PN$", containers.get(0).getPartNumber());
			ZPLCode = ZPLCode.replace("$DES$", modelDescription);
			ZPLCode = ZPLCode.replace("$QTY$", Integer.toString(qtyContainer));
			System.out.println(ZPLCode);
			vMsg = printZpl(ZPLCode, zpl.getIp(), 9100);
		} catch (Exception e) {
			vMsg = "Error: " + e.getMessage();
			e.printStackTrace();
		}
		return vMsg;
	}

	public String printSIEMENS(String ticket, List<Container> containers, long idProject, String project, String modelDescription) {
		String vMsg = "";
		try {
			String ZPLCode = "";
			ZPL zpl = zpldao.findZPLByIdProject(idProject);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			Date date = new Date();
			String formattedDate = formatter.format(date);
			int qtyContainer = 0;
			for (int i = 0; i < containers.size(); i++)
				qtyContainer += containers.get(i).getQty();
			ZPLCode = zpl.getZpl();
			ZPLCode = ZPLCode.replace("$PROJECT$", "SIEMENS");
			ZPLCode = ZPLCode.replace("$DATE$", formattedDate);
			ZPLCode = ZPLCode.replace("$DES$", modelDescription);
			ZPLCode = ZPLCode.replace("$TK$", ticket);
			ZPLCode = ZPLCode.replace("$PN$", containers.get(0).getPartNumber());
			ZPLCode = ZPLCode.replace("$QTY$", Integer.toString(qtyContainer));
//			ZPLCode = ZPLCode.replace("$Instruction$", instruction);
//			ZPLCode = ZPLCode.replace("$poline$", poLine);
			if (containers.get(0).getPartNumber().contains("-R"))
				ZPLCode = ZPLCode.replace("$RMA$", "RMA");
			else
				ZPLCode = ZPLCode.replace("$RMA$", "");
			
			vMsg = printZpl(ZPLCode, zpl.getIp(), 9100);
		} catch (Exception e) {
			vMsg = "Error: " + e.getMessage();
			e.printStackTrace();
		}
		return vMsg;
	}

	public String printDIALIGHT(String ticket, List<Container> containers, long idProject, String PartNumber) {
		String vMsg = "";
		try {
			String ZPLCode = "";
			ZPL zpl = zpldao.findZPLByIdProject(idProject);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			String formattedDate = formatter.format(date);
			int qtyForTicket = 0;
			for(int x = 0; x < containers.size(); x++) {
				qtyForTicket = qtyForTicket + (int) containers.get(x).getQty();
			}
			ZPLCode = zpl.getZpl();
			ZPLCode = ZPLCode.replace("$Date$", formattedDate);
			ZPLCode = ZPLCode.replace("$Ticket$", ticket);
			ZPLCode = ZPLCode.replace("$PartNumber$", containers.get(0).getPartNumber());
			ZPLCode = ZPLCode.replace("$Cantidad$", Integer.toString(qtyForTicket));
//			ZPLCode = ZPLCode.replace("$PO$", poLine);
//			ZPLCode = ZPLCode.replace("$Instruction$", instruccion);
			vMsg = printZpl(ZPLCode, zpl.getIp(), 9100);
			System.out.println(ZPLCode);
		} catch (Exception e) {
			e.printStackTrace();
			vMsg = "Error: " + e.getMessage();
		}
		return vMsg;
	}

	public String printColor(String ticket, List<Container> containers, long idProject, String project) {
		String vMsg = "";
		try {
			String ZPLCode = "";
			ZPL zpl = zpldao.findZPLByIdProject(idProject);
			ZPLCode = zpl.getZpl();
			ZPLCode = ZPLCode.replace("$TK$", ticket);
			int position = 0;
			List<Container> containersPrint = sortContainers(containers);
			for (int i = 0; i < containersPrint.size(); i++) {
				position++;
				System.out.println("Position!!!!!!!" + position);
				ZPLCode = ZPLCode.replace("$PN" + position + "$", containersPrint.get(i).getPartNumber());
				ZPLCode = ZPLCode.replace("$QTY" + position + "$", Long.toString(containersPrint.get(i).getQty()));
				ZPLCode = ZPLCode.replace("$CN" + position + "$", containersPrint.get(i).getPartNumber());
				ZPLCode = ZPLCode.replace("$CY" + position + "$", Long.toString(containersPrint.get(i).getQty()));
			}
			if (10 > position) {
				System.out.println("valor de la posicion" + position);
				for (int x = position + 1; x <= 10; x++) {
					System.out.println("Valor de x" + x);
					ZPLCode = ZPLCode.replace("$PN" + x + "$", "");
					ZPLCode = ZPLCode.replace("$QTY" + x + "$", "");
					ZPLCode = ZPLCode.replace("BCN,38,N,N,Y^FD$CN" + x + "$", "");
					ZPLCode = ZPLCode.replace("BCN,38,N,N,Y^FD$CY" + x + "$", "");
				}
			}
			vMsg = printZpl(ZPLCode, zpl.getIp(), 9100);
		} catch (Exception e) {
			vMsg = "Error: " + e.getMessage();
			e.printStackTrace();
		}
		return vMsg;
	}

	private List<Container> sortContainers(List<Container> containers) {
		List<Container> sortedContainers = new ArrayList<Container>();
		List<Container> notSortedContainers = new ArrayList<Container>();
		for (Container container : containers) {
			notSortedContainers.add(new Container(container));
		}
		int containerListSize = notSortedContainers.size();
		int sortedContainerListSize = 1;
		boolean found = true;
		// first Scenario
		sortedContainers.add(notSortedContainers.get(0));
		// Rest Scenario
		for (int x = 1; x < containerListSize; x++) {
			sortedContainerListSize = sortedContainers.size();
			for (int y = 0; y < sortedContainerListSize; y++) {
				// Part Number comparasion
				System.out.println("X:" + x + " Y:" + y);
				System.out.println("sortedContainerListSize: " + sortedContainerListSize + " containerListSize:"
						+ containerListSize);
				if (notSortedContainers.get(x).getPartNumber().equals(sortedContainers.get(y).getPartNumber())) {
					sortedContainers.get(y).suma(notSortedContainers.get(x));
					y = sortedContainerListSize;
					found = true;
				} else {
					found = false;
				}
			}
			if (!found)
				sortedContainers.add(notSortedContainers.get(x));
		}
		// Testing
		System.out.println("The Containers list got:");
		for (Container container : sortedContainers) {
			System.out.println("Container:" + container.getContainer() + "PN: " + container.getPartNumber() + " Size:"
					+ container.getQty());
		}
		return sortedContainers;
	}

	public String printZpl(String zpl, String ip, int port) throws Exception {
		String vMsg = "";
		Socket clientSocket = null;
		try {
			clientSocket = new Socket(ip, port);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			outToServer.writeBytes(zpl);
			clientSocket.close();
			if (clientSocket.isClosed()) {
				vMsg = "Success";
			} else {
				vMsg = "Fail";
			}
		} catch (Exception e) {
			vMsg = "ZEBRA_NOT_FOUND " + e;
			throw new Exception("ZEBRA_NOT_FOUND " + e);
		} finally {
			if (clientSocket != null) {
				clientSocket.close();
			}
		}
		return vMsg;
	}
}