package com.sanmina.skid.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sanmina.tk.orm.model.skid.tbl_skid;

public class BuildPdfSkid {

	private String Container;
	private String revision;
	private List<tbl_skid> Lista;

	public BuildPdfSkid(String Container, String revision, List<tbl_skid> Lista) {
		this.Container = Container;
		this.revision = revision;
		this.Lista = Lista;

	}

	public Document buildDoc(Document document) {
		try {
			String path = "ticketC.pdf";
			File file = new File(path);

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
			document.open();

			PdfContentByte cb = writer.getDirectContent();
			Barcode128 code128 = new Barcode128();
			code128.setBaseline(-1);
			code128.setSize(12);
			code128.setCode(Container);
			code128.setCodeType(Barcode128.CODE128);

			Image img = Image.getInstance("http://localhost:8080/TicketControl/resources/img/img.png");
			img.setWidthPercentage(100);
			img.setAlignment(Element.ALIGN_CENTER);

			Image code128Image = code128.createImageWithBarcode(cb, null, null);

			code128Image.setWidthPercentage(20);

			PdfPCell cellCode = new PdfPCell(code128Image);
			cellCode.setBorder(PdfPCell.NO_BORDER);

			Paragraph paragraphNewLine = new Paragraph();
			paragraphNewLine.add("\n");

			PdfPTable tblTitle = new PdfPTable(4);
			tblTitle.setWidthPercentage(100);

			PdfPTable tblSerial = new PdfPTable(Lista.size());
			tblSerial.setWidthPercentage(100);

			// tblSerial.setWidths(columnDataWidths);
			Paragraph pContainer = new Paragraph("Skid");
			pContainer.setAlignment(Element.ALIGN_LEFT);
			PdfPCell cellContainer = new PdfPCell(pContainer);
			cellContainer.setBorder(PdfPCell.NO_BORDER);

			Paragraph pPart = new Paragraph(Lista.get(0).getPartNumber());
			pPart.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cellPart = new PdfPCell(new Paragraph("Part Print: \n"+pPart));
			cellPart.setBorder(PdfPCell.NO_BORDER);
			Paragraph pRevision = new Paragraph(revision);
			pRevision.setAlignment(Element.ALIGN_RIGHT);
			PdfPCell cellRevision = new PdfPCell(new Paragraph("Revision: \n"+pRevision));
			cellRevision.setBorder(PdfPCell.NO_BORDER);

			tblTitle.addCell(cellContainer);
			tblTitle.addCell(cellCode);
			tblTitle.addCell(cellPart);
			tblTitle.addCell(cellRevision);

			for (int i = 0; i < Lista.size(); i++) {
				Barcode128 codeSerial = new Barcode128();
				codeSerial.setBaseline(-1);
				codeSerial.setSize(12);
				codeSerial.setCode(Lista.get(i).getSerial());
				codeSerial.setCodeType(Barcode128.CODE128);
				Image codeImageSerial = codeSerial.createImageWithBarcode(cb, null, null);

				codeImageSerial.setWidthPercentage(20);

				PdfPCell cell = new PdfPCell(codeImageSerial);
				cell.setBorder(PdfPCell.NO_BORDER);
				tblSerial.addCell(cell);
			}

			PdfPTable tblFooter = new PdfPTable(2);
			tblFooter.setWidthPercentage(100);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String formattedDate = formatter.format(date);
			Paragraph pDate = new Paragraph("Print Date " + formattedDate);
			pContainer.setAlignment(Element.ALIGN_LEFT);
			PdfPCell cellDate = new PdfPCell(pDate);
			cellDate.setBorder(PdfPCell.NO_BORDER);
			Paragraph pQty = new Paragraph(Integer.toString(Lista.size()));
			pContainer.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cellQty = new PdfPCell(new Paragraph("Qty: \n"+pQty));
			cellQty.setBorder(PdfPCell.NO_BORDER);

			tblFooter.addCell(cellDate);
			tblFooter.addCell(cellQty);

			/** footer */
			document.add(img);
			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(tblTitle);

			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(tblSerial);
			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(paragraphNewLine);
			document.add(tblFooter);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}
}