package com.sanmina.tk.pdf;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
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
import com.sanmina.tk.orm.model.TicketPartial;

import lombok.Getter;
import lombok.Setter;

public class BuildPdfDishPartial {

	// @Getter
	// @Setter
	// private String partNumber;

	// @Getter
	// @Setter
	// private String ticket;

	// @Getter
	// @Setter
	// private String project;

	// @Getter
	// @Setter
	// private String description;

	// @Getter
	// @Setter
	// private String container;

	// @Getter
	// @Setter
	// private Long qty;

	@Getter
	@Setter
	private List<TicketPartial> partial;

	public BuildPdfDishPartial(List<TicketPartial> partial) {
		this.partial = partial;
	}

	public Document buildDoc(Document document) {
		try {
			System.out.println("Cantidad de paginas a imprimir " + partial.size());

			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("ticketC.pdf"));
			document.open();
			PdfContentByte cb = pdfWriter.getDirectContent();
			for (int i = 0; i < partial.size(); i++) {
				Barcode128 barcodeTicket = new Barcode128();
				barcodeTicket.setCode(partial.get(i).getTicket());
				barcodeTicket.setCodeType(Barcode128.CODE128);

				Image imgCodeTicket = barcodeTicket.createImageWithBarcode(cb, null, null);
				imgCodeTicket.setAbsolutePosition(200, 700);
				imgCodeTicket.scalePercent(100);
				imgCodeTicket.setAlignment(Element.ALIGN_LEFT);

				Barcode128 barcodePart = new Barcode128();
				barcodePart.setCode(partial.get(i).getPartNumber());
				barcodePart.setCodeType(Barcode128.CODE128);

				Image imgCodePart = barcodePart.createImageWithBarcode(cb, null, null);
				imgCodePart.setAbsolutePosition(200, 700);
				imgCodePart.scalePercent(100);
				imgCodePart.setAlignment(Element.ALIGN_CENTER);

				Barcode128 barcodeQty = new Barcode128();
				barcodeQty.setCode(partial.get(i).getQty().toString());
				barcodeQty.setCodeType(Barcode128.CODE128);

				Barcode128 barcodeSkid = new Barcode128();
				barcodeSkid.setCode(partial.get(i).getPallet());
				barcodeSkid.setCodeType(Barcode128.CODE128);

				Image imgCodeQty = barcodeQty.createImageWithBarcode(cb, null, null);
				imgCodeQty.setAbsolutePosition(200, 700);
				imgCodeQty.scalePercent(100);
				imgCodeQty.setAlignment(Element.ALIGN_LEFT);

				Image imgCodeSkid = barcodeSkid.createImageWithBarcode(cb, null, null);
				imgCodeSkid.setAbsolutePosition(200, 700);
				imgCodeSkid.scalePercent(100);
				imgCodeSkid.setAlignment(Element.ALIGN_LEFT);

				Font arial15 = FontFactory.getFont("Arial", 15);
				Font arialBold18 = FontFactory.getFont("Arial", 18);
				arialBold18.setStyle(Font.BOLD);

				Image img = Image.getInstance("http://localhost:8080/TicketControlAdvance/resources/img/sanmina.png");
				img.setWidthPercentage(100);
				img.setAlignment(Element.ALIGN_CENTER);

				Paragraph title = new Paragraph("SANMINA PLANTA 447" + "\n" + "\n" + "Ticket Control Dish", arial15);
				title.setAlignment(Element.ALIGN_CENTER);
				PdfPCell cellTitle = new PdfPCell(title);
				cellTitle.setBorder(PdfPCell.NO_BORDER);

				PdfPCell cellTicket = new PdfPCell(
						new Paragraph("Ticket   " + "\n" + partial.get(i).getTicket(), arial15));
				PdfPCell cellCodeTicket = new PdfPCell(imgCodeTicket);
				cellTicket.setBorder(PdfPCell.NO_BORDER);
				cellCodeTicket.setBorder(PdfPCell.NO_BORDER);

				PdfPCell cellPart = new PdfPCell(
						new Paragraph("Part Number   " + "\n" + partial.get(i).getPartNumber(), arial15));
				PdfPCell cellCodePart = new PdfPCell(imgCodePart);
				cellPart.setBorder(PdfPCell.NO_BORDER);
				cellCodePart.setBorder(PdfPCell.NO_BORDER);

				PdfPCell cellDes = new PdfPCell(new Paragraph("Description   ", arial15));
				PdfPCell cellInfoDes = new PdfPCell(new Paragraph(partial.get(i).getDescription()));
				cellDes.setBorder(PdfPCell.NO_BORDER);
				cellInfoDes.setBorder(PdfPCell.NO_BORDER);

				PdfPCell cellQty = new PdfPCell(new Paragraph("Qty   " + "\n" + partial.get(i).getQty(), arial15));
				PdfPCell cellCodeQty = new PdfPCell(imgCodeQty);
				cellQty.setBorder(PdfPCell.NO_BORDER);
				cellCodeQty.setBorder(PdfPCell.NO_BORDER);

				PdfPCell cellSkid = new PdfPCell();
				PdfPCell cellCodeSkid = new PdfPCell();
				cellSkid.setBorder(PdfPCell.NO_BORDER);
				cellCodeSkid.setBorder(PdfPCell.NO_BORDER);
				// if(!project.contains("VERIZON")) {
				cellSkid.addElement(new Paragraph("Skid   " + "\n" + partial.get(i).getPallet(), arial15));
				cellCodeSkid.addElement(imgCodeSkid);
				// }

				PdfPTable tblContainer = new PdfPTable(2);
				tblContainer.setWidthPercentage(100);
				tblContainer.addCell(cellSkid);
				tblContainer.addCell(cellCodeSkid);
				tblContainer.setHorizontalAlignment(Element.ALIGN_LEFT);

				PdfPTable tblTicket = new PdfPTable(2);
				tblTicket.setWidthPercentage(100);
				tblTicket.addCell(cellTicket);
				tblTicket.addCell(cellCodeTicket);
				tblTicket.setHorizontalAlignment(Element.ALIGN_LEFT);

				PdfPTable tblDescription = new PdfPTable(2);
				tblDescription.setWidthPercentage(100);
				tblDescription.addCell(cellDes);
				tblDescription.addCell(cellInfoDes);
				tblDescription.setHorizontalAlignment(Element.ALIGN_LEFT);

				PdfPTable tblPart = new PdfPTable(2);
				tblPart.setWidthPercentage(100);
				tblPart.addCell(cellPart);
				tblPart.addCell(cellCodePart);
				tblPart.setHorizontalAlignment(Element.ALIGN_LEFT);

				PdfPTable tblQty = new PdfPTable(2);
				tblQty.setWidthPercentage(100);
				tblQty.addCell(cellQty);
				tblQty.addCell(cellCodeQty);
				tblQty.setHorizontalAlignment(Element.ALIGN_LEFT);

				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				String formattedDate = formatter.format(date);
				PdfPCell cellDate = new PdfPCell(new Paragraph(formattedDate, arial15));
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
				document.add(tblPart);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("\n"));
				document.add(tblDescription);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("\n"));
				document.add(tblQty);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("\n"));
				document.add(tblContainer);
				document.newPage();
				System.out.println("Se agrega una nueva pagina :D");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

}
