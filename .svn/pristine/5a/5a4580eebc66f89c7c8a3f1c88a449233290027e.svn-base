package com.sanmina.tk.csv;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sanmina.tk.orm.model.PoLineExisting;

public class ManagerCSV {

	public static InputStream getTestFile() throws IOException {
		List<String> lines = Arrays.asList("The first line", "The second line");
		Path file = Paths.get("the-file-name.txt");
		Files.write(file, lines, Charset.forName("UTF-8"));
		System.out.println("File Location: " + file.toAbsolutePath().toString());
		InputStream is = new FileInputStream(file.toAbsolutePath().toString());
		return is;
	}

	public static InputStream getFile(List<PoLineExisting> poLines) {
		try {
			String filename = "tempPoLines.xls";
			int index = 1;
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(filename);
			HSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell(0).setCellValue("Po Line");
			rowhead.createCell(1).setCellValue("Part Number");
			for (PoLineExisting pE : poLines) {
				HSSFRow row = sheet.createRow((short) index);
				row.createCell(0).setCellValue(pE.getPoLine());
				row.createCell(1).setCellValue(pE.getPartNumber());
				index = index + 1;
			}
			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			workbook.close();
			fileOut.close();
			System.out.println("Your excel file has been generated!");
			InputStream is = new FileInputStream(filename);
			return is;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
