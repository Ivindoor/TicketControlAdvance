package com.sanmina.tk.pdf;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sanmina.tk.orm.model.Container;

import lombok.Getter;
import lombok.Setter;

public class BuildPdfCOLOR {

	@Getter
	@Setter
	private List<Container> containers;

	@Getter
	@Setter
	private String ticket;

	@Getter
	@Setter
	private String project;
	
	public BuildPdfCOLOR(List<Container> containers, String ticket, String project) {
		this.containers = containers;
		this.ticket = ticket;
		this.project = project;
	}
	
	public Document buildDoc(Document document) {
		try {
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("ticketC.pdf"));
			document.open();
			
			PdfContentByte cb = pdfWriter.getDirectContent();
			
			Barcode128 barcodeTicket = new Barcode128();
			barcodeTicket.setCode(ticket);
			barcodeTicket.setCodeType(Barcode128.CODE128);
			
			Image imgCodeTicket = barcodeTicket.createImageWithBarcode(cb, null, null);
			imgCodeTicket.setAbsolutePosition(200, 700);
			imgCodeTicket.scalePercent(100);
			imgCodeTicket.setAlignment(Element.ALIGN_LEFT);
			
			Font arial15 = FontFactory.getFont("Arial", 15);
			Font arialBold18 = FontFactory.getFont("Arial", 18);
			arialBold18.setStyle(Font.BOLD);
			
			Image img = Image.getInstance("http://localhost:8080/TicketControlAdvance/resources/img/sanmina.png");
			img.setWidthPercentage(100);
			img.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph title = new Paragraph("SANMINA PLANTA 447" + "\n" + "\n" + "Ticket Control " + project, arial15);
			title.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cellTitle = new PdfPCell(title);
			cellTitle.setBorder(PdfPCell.NO_BORDER);
			
			PdfPCell cellTicket = new PdfPCell(new Paragraph("Ticket   " + "\n" + ticket, arial15));
			PdfPCell cellCodeTicket = new PdfPCell(imgCodeTicket);
			cellTicket.setBorder(PdfPCell.NO_BORDER);
			cellCodeTicket.setBorder(PdfPCell.NO_BORDER);
			
			PdfPTable tblpart = new PdfPTable(2);
			tblpart.setWidthPercentage(100);
			
			Paragraph paragraphNewLine = new Paragraph();
			paragraphNewLine.add("\n");
			
			PdfPCell cellBlanck = new PdfPCell(paragraphNewLine);
			cellBlanck.setBorder(PdfPCell.NO_BORDER);
			
			int position = 0;
			List<Container> containersPrint = sortContainers(containers);
			int i;
			for (i = 0; i < containersPrint.size(); i++) {
				position++;
				System.out.println("Position!!!!!!!" + position);
				
//				ZPLCode = ZPLCode.replace("$PN" + position + "$", containersPrint.get(i).getPartNumber());
//				ZPLCode = ZPLCode.replace("$QTY" + position + "$", Long.toString(containersPrint.get(i).getQty()));
				
				Barcode128 codepart = new Barcode128();
				codepart.setCode(containersPrint.get(i).getPartNumber());
				codepart.setSize(10);
				codepart.setBaseline(-1);
				codepart.setCodeType(Barcode128.CODE128);
				Image codeImagePart = codepart.createImageWithBarcode(cb, null, null);
				codeImagePart.setWidthPercentage(20);
				codeImagePart.scalePercent(80);
				
				Barcode128 codeqty = new Barcode128();
				codeqty.setCode(Long.toString(containersPrint.get(i).getQty()));
				codeqty.setSize(10);
				codeqty.setBaseline(-1);
				codeqty.setCodeType(Barcode128.CODE128);
				Image codeImageQty = codeqty.createImageWithBarcode(cb, null, null);
				codeImageQty.setWidthPercentage(20);
				codeImageQty.scalePercent(80);
				
				PdfPCell cell = new PdfPCell(codeImagePart);
				cell.setBorder(PdfPCell.NO_BORDER);
				
				PdfPCell cell2 = new PdfPCell(codeImageQty);
				cell2.setBorder(PdfPCell.NO_BORDER);
				
				tblpart.addCell(cell);
				tblpart.addCell(cell2);
				
				tblpart.addCell(cellBlanck);
				tblpart.addCell(cellBlanck);
			}

//			if (i % 4 != 0) {
//				int cellFinals = 4 - (i % 4);
//				System.out.println("Numero de celdas faltantes " + cellFinals);
//				for (int x = 0; x < cellFinals; x++) {
//					System.out.println("Numero de vueltas en el for por celda faltante " + x);
//					PdfPCell cell = new PdfPCell();
//					cell.setBorder(PdfPCell.NO_BORDER);
//					tblpart.addCell(cell);
//				}
//			}
			
			
//			PdfPTable tblContainer = new PdfPTable(1);
//			tblContainer.setWidthPercentage(100);
//			tblContainer.addCell(cellContainer);
//			tblContainer.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			PdfPTable tblTicket = new PdfPTable(2);
			tblTicket.setWidthPercentage(100);
			tblTicket.addCell(cellTicket);
			tblTicket.addCell(cellCodeTicket);
			tblTicket.setHorizontalAlignment(Element.ALIGN_LEFT);
			
//			PdfPTable tblDescription = new PdfPTable(2);
//			tblDescription.setWidthPercentage(100);
//			tblDescription.addCell(cellDes);
//			tblDescription.addCell(cellInfoDes);
//			tblDescription.setHorizontalAlignment(Element.ALIGN_LEFT);
			
//			PdfPTable tblPart = new PdfPTable(2);
//			tblPart.setWidthPercentage(100);
//			tblPart.addCell(cellPart);
//			tblPart.addCell(cellCodePart);
//			tblPart.setHorizontalAlignment(Element.ALIGN_LEFT);
			
//			PdfPTable tblQty = new PdfPTable(2);
//			tblQty.setWidthPercentage(100);
//			tblQty.addCell(cellQty);
//			tblQty.addCell(cellCodeQty);
//			tblQty.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			String formattedDate = formatter.format(date);
			PdfPCell cellDate = new PdfPCell( new Paragraph(formattedDate, arial15));
			cellDate.setBorder(PdfPCell.NO_BORDER);
			PdfPCell cellImg = new PdfPCell(img);
			cellImg.setBorder(PdfPCell.NO_BORDER);
			
			
			PdfPTable tblHeader = new PdfPTable(3);
			tblHeader.setWidthPercentage(100);
			tblHeader.addCell(cellImg);
			tblHeader.addCell(cellTitle);
			tblHeader.addCell(cellDate);
			tblHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			
			document.add(tblHeader);
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(tblTicket);
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(tblpart);
//			document.add(new Paragraph("\n"));
//			document.add(new Paragraph("\n"));
//			document.add(tblDescription);
//			document.add(new Paragraph("\n"));
//			document.add(new Paragraph("\n"));
//			document.add(tblQty);
//			document.add(new Paragraph("\n"));
//			document.add(new Paragraph("\n"));
//			document.add(tblContainer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
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
}
