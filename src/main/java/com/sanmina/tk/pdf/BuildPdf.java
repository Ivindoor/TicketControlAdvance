package com.sanmina.tk.pdf;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import lombok.Getter;
import lombok.Setter;

public class BuildPdf {

	@Getter
	@Setter
	private String partNumber;

	@Getter
	@Setter
	private String ticket;

	@Getter
	@Setter
	private String project;

	@Getter
	@Setter
	private Long qty;

	public BuildPdf(String partNumber, String ticket, String project, long qty) {
		this.partNumber = partNumber;
		this.ticket = ticket;
		this.project = project;
		this.qty = qty;
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
			
			Barcode128 barcodePart = new Barcode128();
			barcodePart.setCode(partNumber);
			barcodePart.setCodeType(Barcode128.CODE128);
			
			Image imgCodePart = barcodePart.createImageWithBarcode(cb, null, null);
			imgCodePart.setAbsolutePosition(200, 700);
			imgCodePart.scalePercent(100);
			imgCodePart.setAlignment(Element.ALIGN_CENTER);
			
			Barcode128 barcodeQty = new Barcode128();
			barcodeQty.setCode(qty.toString());
			barcodeQty.setCodeType(Barcode128.CODE128);
			
			Image imgCodeQty = barcodeQty.createImageWithBarcode(cb, null, null);
			imgCodeQty.setAbsolutePosition(200, 700);
			imgCodeQty.scalePercent(100);
			imgCodeQty.setAlignment(Element.ALIGN_LEFT);
			
			Font arial15 = FontFactory.getFont("Arial", 15);
			Font arialBold18 = FontFactory.getFont("Arial", 18);
			arialBold18.setStyle(Font.BOLD);
			
			Image img = Image.getInstance("http://localhost:8080/TicketControlAdvance/resources/img/sanmina.png");
			img.setWidthPercentage(100);
			img.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph title = new Paragraph("Ticket Control " + project, arialBold18);
			title.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cellTitle = new PdfPCell(title);
			cellTitle.setBorder(PdfPCell.NO_BORDER);
			
			PdfPCell cellTicket = new PdfPCell(new Paragraph("Ticket:   " + "\n" + ticket, arial15));
			PdfPCell cellCodeTicket = new PdfPCell(imgCodeTicket);
			cellTicket.setBorder(PdfPCell.NO_BORDER);
			cellCodeTicket.setBorder(PdfPCell.NO_BORDER);
			
			PdfPCell cellPart = new PdfPCell(new Paragraph("Part Number:   " + "\n" + partNumber, arial15));
			PdfPCell cellCodePart = new PdfPCell(imgCodePart);
			cellPart.setBorder(PdfPCell.NO_BORDER);
			cellCodePart.setBorder(PdfPCell.NO_BORDER);
			
			PdfPCell cellQty = new PdfPCell(new Paragraph("Qty:   " + "\n" + qty, arial15));
			PdfPCell cellCodeQty = new PdfPCell(imgCodeQty);
			cellQty.setBorder(PdfPCell.NO_BORDER);
			cellCodeQty.setBorder(PdfPCell.NO_BORDER);
			
			PdfPTable tblTicket = new PdfPTable(2);
			tblTicket.setWidthPercentage(45);
			tblTicket.addCell(cellTicket);
			tblTicket.addCell(cellCodeTicket);
			tblTicket.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			PdfPTable tblPart = new PdfPTable(2);
			tblPart.setWidthPercentage(80);
			tblPart.addCell(cellPart);
			tblPart.addCell(cellCodePart);
			tblPart.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			PdfPTable tblQty = new PdfPTable(2);
			tblQty.setWidthPercentage(45);
			tblQty.addCell(cellQty);
			tblQty.addCell(cellCodeQty);
			tblQty.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			Date date = new Date();
			String formattedDate = formatter.format(date);
			PdfPCell cellDate = new PdfPCell( new Paragraph(formattedDate, arialBold18));
			cellDate.setBorder(PdfPCell.NO_BORDER);
			
			PdfPTable tblHeader = new PdfPTable(2);
			tblHeader.setWidthPercentage(65);
			tblHeader.addCell(cellTitle);
			tblHeader.addCell(cellDate);
			tblHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
			
			
			document.add(img);
			document.add(new Paragraph("\n"));
			document.add(tblHeader);
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(tblTicket);
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(tblPart);
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(tblQty);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}
}
