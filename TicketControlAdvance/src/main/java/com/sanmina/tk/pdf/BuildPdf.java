package com.sanmina.tk.pdf;

import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import lombok.Getter;
import lombok.Setter;

public class BuildPdf {

	@Autowired
	private String partNumber;
	@Getter
	@Setter
	
	@Autowired
	private String ticket;
	@Getter
	@Setter
	
	@Autowired
	private String project;
	@Getter
	@Setter
	
	@Autowired
	private Integer qty;

	public BuildPdf(String partNumber, String ticket, String project, Integer qty) {
		this.partNumber = partNumber;
		this.ticket = ticket;
		this.project = project;
		this.qty = qty;
	}

	public Document buildDoc(Document document) {
		try {
			Paragraph paragraphNewLine = new Paragraph();
			Paragraph paraTitle = new Paragraph();
			float[] columnWidths = { 2f, 2f, 0.6f };
			float[] columnDataWidths = { 1.2f, 2f, 1.5f, 2f };
			paragraphNewLine.add("\n");
			Font arialBold12 = FontFactory.getFont("Arial", 12);
			arialBold12.setStyle(Font.BOLD | Font.UNDERLINE);
			Font arial10 = FontFactory.getFont("Arial", 10);
			Font arial10Underline = FontFactory.getFont("Arial", 10);
			arial10Underline.setStyle(Font.UNDERLINE);
			Font arialBold10 = FontFactory.getFont("Arial", 10);
			arialBold10.setStyle(Font.BOLD);
			Font arialBold14 = FontFactory.getFont("Arial", 14);
			arialBold14.setStyle(Font.BOLD);

			/** Header Certified Document */
			Image img = Image.getInstance("http://localhost:8080/TicketControl/resources/img/logo.png");
			img.setAbsolutePosition(280f, 630f);
			img.scaleToFit(180f, 180f);
			Paragraph SanminaInformation = new Paragraph();
			SanminaInformation.add(new Paragraph("SANMINA 447", arialBold12));
			Paragraph address = new Paragraph();
			Chunk Address = new Chunk("\n\nCarr Guadalajara-Chapala Km 15.5 No. 29", arial10);
			address.add(Address);
			address.setLeading(0.0f, 2.0f);
			SanminaInformation.add(address);
			SanminaInformation.add(new Paragraph("\n46540 Tlajomulco de Zuñiga, Jalisco México", arial10));
			SanminaInformation.add(new Paragraph("\nTel: 011(52 33) 36689800", arial10));
			SanminaInformation.add(new Paragraph("\nFax: 011 (52 33) 36885640", arial10));
			SanminaInformation.setLeading(0.0f, 20.0f);
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100);
			table.setWidths(columnWidths);

			PdfPCell cell1 = new PdfPCell(SanminaInformation);
			PdfPCell cell2 = new PdfPCell(img);
			PdfPCell cell3 = new PdfPCell(new Paragraph("ANVERSO", arial10));

			cell1.setBorder(PdfPCell.NO_BORDER);
			cell2.setBorder(PdfPCell.NO_BORDER);
			cell3.setBorder(PdfPCell.NO_BORDER);

			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			/** Header Certified Document */
			/** Title */
			Chunk title = new Chunk("Ticket Control " + project, arialBold14);
			paraTitle.add(title);
			paraTitle.setAlignment(Element.ALIGN_CENTER);
			/** Title */
			/** Content Data */
			PdfPTable tableData = new PdfPTable(4);
			tableData.setWidthPercentage(100);
			tableData.setWidths(columnDataWidths);

			PdfPCell cellData1 = new PdfPCell(new Paragraph("Ticket: ", arial10));
			PdfPCell cellData2 = new PdfPCell(new Paragraph(ticket, arial10));

			PdfPCell cellData5 = new PdfPCell(new Paragraph("Numero de parte: ", arial10));
			PdfPCell cellData6 = new PdfPCell(new Paragraph(partNumber, arial10));

			PdfPCell cellData9 = new PdfPCell(new Paragraph("Catidad: ", arial10));
			PdfPCell cellData10 = new PdfPCell(new Paragraph(qty.toString(), arial10));
			PdfPCell cellData11 = new PdfPCell(new Paragraph("No. Certificado:", arial10));
			PdfPCell cellData12 = new PdfPCell(new Paragraph("", arial10));

			PdfPCell cellData13 = new PdfPCell(new Paragraph("Proyecto: ", arial10));
			PdfPCell cellData14 = new PdfPCell(new Paragraph(project, arial10));
			PdfPCell cellData16 = new PdfPCell();

			cellData1.setBorder(PdfPCell.NO_BORDER);
			cellData2.setBorder(PdfPCell.BOTTOM);

			cellData5.setBorder(PdfPCell.NO_BORDER);
			cellData6.setBorder(PdfPCell.BOTTOM);

			cellData9.setBorder(PdfPCell.NO_BORDER);
			cellData10.setBorder(PdfPCell.BOTTOM);
			cellData11.setBorder(PdfPCell.NO_BORDER);
			cellData12.setBorder(PdfPCell.BOTTOM);

			cellData13.setBorder(PdfPCell.NO_BORDER);
			cellData14.setBorder(PdfPCell.BOTTOM);
			cellData16.setBorder(PdfPCell.BOTTOM);

			tableData.addCell(cellData1);
			tableData.addCell(cellData2);
			tableData.addCell(cellData5);
			tableData.addCell(cellData6);
			tableData.addCell(cellData9);
			tableData.addCell(cellData10);
			tableData.addCell(cellData11);
			tableData.addCell(cellData12);
			tableData.addCell(cellData13);
			tableData.addCell(cellData14);
			tableData.addCell(cellData16);
			/** Content Data */
			Chunk station = new Chunk("            Estación", arialBold10);
			Chunk stationInfo = new Chunk(
					"\n            SMT TOP \n            VCD\n            PIH TOP\n            PIH BOT\n            ICT\n              Seriales anexos pasaron prueba de ICT\n            FVT\n               Seriales anexos pasaron prueba de FVT\n            CONFORMAL( 5 a 8 milésimas de pulgada) \n            OBA (Auditoria final)",
					arial10);
			Chunk Sign = new Chunk("Firma:_________________________ \nTitulo: Ingeniero de Calidad", arial10);
			Paragraph sign = new Paragraph();
			sign.add(Sign);
			sign.setAlignment(Element.ALIGN_CENTER);

			/** footer */
			Chunk SOP = new Chunk(
					"SOP-F-15365-447                                       EDICION 6                                                  SOP-Q-10203-447",
					arial10);

			/** footer */

			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(table);
			document.add(paraTitle);
			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(tableData);
			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(new Paragraph(
					"Sanmina certifica por este medio que las tarjetas arriba descritas cumplen con \nIPC A-610 vigente Clase 2 para el ensamble en general \ny Clase 3 para los puntos indicados en la especificacion "
							+ "" + "\ny que fueron procesadas y probadas en (donde aplica) las siguientes estaciones:",
					arial10));
			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(station);
			document.add(stationInfo);
			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(sign);
			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(SOP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}
}
