package com.sanmina.tk.zpl;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sanmina.tk.pdf.BuildPdf;

import lombok.Getter;
import lombok.Setter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.sanmina.tk.orm.dao.ZPLDao;
import com.sanmina.tk.orm.model.Container;
import com.sanmina.tk.orm.model.ZPL;

public class PrintZPL {

	ZPLDao zpldao;
	private static String ZPLCode;
	@Getter
	@Setter
	private InputStream inputStream;

	public PrintZPL(ZPLDao zpldao) {
		this.zpldao = zpldao;
	}

	public Boolean printHAAS(String ticket, List<Container> containers, long idProject, String project, boolean RMA) {
		boolean statusPrint = false;
		try {
			ZPL zpl = zpldao.findZPLByIdProject(idProject);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String formattedDate = formatter.format(date);
			ZPLCode = zpl.getZpl();
			ZPLCode = ZPLCode.replace("$DATE$", formattedDate);
			ZPLCode = ZPLCode.replace("$PROJECT$", project);
			ZPLCode = ZPLCode.replace("$TK$", ticket);
			ZPLCode = ZPLCode.replace("$PN$", containers.get(0).getPartNumber());
			ZPLCode = ZPLCode.replace("$QTY$", Integer.toString(containers.size()));
			if (RMA == true)
				ZPLCode = ZPLCode.replace("$RMA$", "RMA");
			else
				ZPLCode = ZPLCode.replace("$RMA$", "");
			printZpl(ZPLCode, zpl.getIp(), 9100);
			statusPrint = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusPrint;
	}

	public Boolean printNOKIA(String ticket, List<Container> containers, long idProject, String project,
			String revision) {
		boolean statusPrint = false;
		try {
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
			printZpl(ZPLCode, zpl.getIp(), 9100);
			statusPrint = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusPrint;
	}

	public Boolean printDigital(String ticket, List<Container> containers, long idProject, String project) {
		return null;
	}

	public Boolean printMCE(String ticket, List<Container> containers, long idProject, String project) {
		boolean statusPrint = false;
		try {
			OutputStream file = new FileOutputStream(new File("TicketPDF.pdf"));
	        Document document = new Document();
	        document.setMargins(60, 65, 50, 0);
	        BuildPdf doc = new BuildPdf(ticket, containers.get(0).getPartNumber(), project, containers.size());
	        try {
	            PdfWriter.getInstance(document, file);
	            document.open();
	            doc.buildDoc(document);
	            document.close();
	            statusPrint = true;
	        } catch (DocumentException e) {
	        	statusPrint = false;
	            e.printStackTrace();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusPrint;
	}

	public Boolean printSIEMENS(String ticket, List<Container> containers, long idProject, String project,
			boolean RMA) {
		boolean statusPrint = false;
		try {
			ZPL zpl = zpldao.findZPLByIdProject(idProject);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			Date date = new Date();
			String formattedDate = formatter.format(date);
			ZPLCode = zpl.getZpl();
			ZPLCode = ZPLCode.replace("$DATE$", formattedDate);
			ZPLCode = ZPLCode.replace("$DES$", "");
			ZPLCode = ZPLCode.replace("$TK$", ticket);
			ZPLCode = ZPLCode.replace("$PN$", containers.get(0).getPartNumber());
			ZPLCode = ZPLCode.replace("$QTY$", Integer.toString(containers.size()));
			if (RMA == true)
				ZPLCode = ZPLCode.replace("$RMA$", "RMA");
			else
				ZPLCode = ZPLCode.replace("$RMA$", "");
			printZpl(ZPLCode, zpl.getIp(), 9100);
			statusPrint = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusPrint;
	}

	public Boolean printDIALIGHT(String ticket, List<Container> containers, long idProject, String project) {
		boolean statusPrint = false;
		try {
			ZPL zpl = zpldao.findZPLByIdProject(idProject);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			System.out.println(date.toString());
			String formattedDate = formatter.format(date);
			ZPLCode = zpl.getZpl();
			ZPLCode = ZPLCode.replace("$DATE$", formattedDate);
			ZPLCode = ZPLCode.replace("$TK$", ticket);
			ZPLCode = ZPLCode.replace("$PN$", containers.get(0).getPartNumber());
			ZPLCode = ZPLCode.replace("$QTY$", Integer.toString(containers.size()));
			printZpl(ZPLCode, zpl.getIp(), 9100);
			statusPrint = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusPrint;
	}

	public Boolean printColor(String ticket, List<Container> containers, long idProject, String project) {
		boolean statusPrint = false;
		try {
			ZPL zpl = zpldao.findZPLByIdProject(idProject);
			ZPLCode = zpl.getZpl();
			ZPLCode = ZPLCode.replace("$TK$", ticket);
			for (int i = 0; i < containers.size(); i++) {
				int count = 10 - containers.size();
				ZPLCode = ZPLCode.replace("$PN" + i + "$", containers.get(i).getPartNumber());
				ZPLCode = ZPLCode.replace("$QTY" + i + "$", Integer.toString(containers.size()));
				if (count > i) {
					ZPLCode = ZPLCode.replace("$PN" + count + "$", "");
					ZPLCode = ZPLCode.replace("$QTY" + count + "$", "");
				}
			}
			printZpl(ZPLCode, zpl.getIp(), 9101);
			statusPrint = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusPrint;
	}

	public static void printZpl(String zpl, String ip, int port) throws Exception {
		Socket clientSocket = null;
		try {
			clientSocket = new Socket(ip, port);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			outToServer.writeBytes(zpl);
			clientSocket.close();
		} catch (Exception e) {
			throw new Exception("ZEBRA_NOT_FOUND " + e);
		} finally {
			if (clientSocket != null) {
				clientSocket.close();
			}
		}
	}

	// public static void projectPdf() {
	// Document doc = new Document();
	// try {
	// PdfWriter writer = PdfWriter.getInstance(doc, new
	// FileOutputStream("HelloWorld.pdf"));
	// doc.open();
	// doc.add(new Paragraph("A Hello World PDF document."));
	// doc.close();
	// writer.close();
	// } catch (DocumentException e) {
	// e.printStackTrace();
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// }
	// }
}
