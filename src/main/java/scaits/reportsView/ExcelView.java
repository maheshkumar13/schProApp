package scaits.reportsView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFDrawing;
import org.apache.poi.xssf.streaming.SXSSFPicture;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;


import scaits.bo.employee.EmployeeBO;
import scaits.util.Constants.FormatDates;

public class ExcelView {

	public static void buildExcelDocument(List<Object[]> totalResults, Set<String> colNames, SXSSFWorkbook wb,
			HttpServletRequest request, HttpServletResponse response, String reportName, EmployeeBO employee,
			String dateStr) throws Exception {

		// change the file name

		// create excel xls sheet
		SXSSFSheet sheet = wb.createSheet(reportName);
		sheet.setDefaultColumnWidth((short) 12);
		int currentRow = 0;
		short currentColumn = 0;
		// CREATE STYLE FOR HEADER
		XSSFCellStyle headerStyle = (XSSFCellStyle) wb.createCellStyle();
		XSSFFont headerFont = (XSSFFont) wb.createFont();
		headerFont.setBold(true);
		headerStyle.setFont(headerFont);

		XSSFCellStyle headStyle = (XSSFCellStyle) wb.createCellStyle();
		XSSFFont headFont = (XSSFFont) wb.createFont();
		headFont.setBold(true);
		headFont.setColor(IndexedColors.ORANGE.getIndex());
		headStyle.setFont(headFont);
		// POPULATE HEADER COLUMNS
		SXSSFRow headerRow = sheet.createRow(5);

		// create style for header cells
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("Arial");
		/* style.setFillForegroundColor(XSSFColor.toXSSFColor(org.apa); */
		/* font.setColor(HSSFColor.WHITE.index); */
		style.setFont(font);
		/*
		 * InputStream logo = new
		 * FileInputStream("/home/fmsreports/reports/images/logo_scil1.png");
		 */

		String respath = "/static/assets/logo_scil1.png";
		InputStream logo = ExcelView.class.getResourceAsStream(respath);
		if (logo == null)
			throw new Exception("resource not found: " + respath);
		/* Read the input image into InputStream */
		/* Convert Image to byte array */
		byte[] bytes = IOUtils.toByteArray(logo);
		/* Add Picture to workbook and get a index for the picture */
		int my_picture_id = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
		/* Close Input Stream */
		logo.close();
		/* Create an anchor point */
		ClientAnchor my_anchor = new XSSFClientAnchor();
		/*
		 * Define top left corner, and we can resize picture suitable from there
		 */
		my_anchor.setCol1(1);
		my_anchor.setRow1(1);
		SXSSFDrawing drawing = sheet.createDrawingPatriarch();
		SXSSFPicture my_picture = drawing.createPicture(my_anchor, my_picture_id);
		my_picture.resize();
		SXSSFRow headerRow1 = sheet.createRow(1);
		SXSSFCell cell1 = headerRow1.createCell(3);
		cell1.setCellValue("Sri Chaitanya Educational Institutions");
		cell1.setCellStyle(headStyle);

		SXSSFCell cell2 = headerRow1.createCell(7);
		cell2.setCellValue("HYDERABAD");
		cell2.setCellStyle(headStyle);

		SXSSFRow headerRow2 = sheet.createRow(2);
		SXSSFCell cell3 = headerRow2.createCell(2);
		cell3.setCellValue("(Admin. by Sri Kalyana Chakravarti Memorial Educational Trust)");
		cell3.setCellStyle(headStyle);

		SXSSFCell cell4 = headerRow2.createCell(7);
		if (employee != null) {
			cell4.setCellValue("" + employee.getCampus());
		} else {
			cell4.setCellValue("");
		}

		cell4.setCellStyle(headStyle);

		SXSSFRow headerRow3 = sheet.createRow(3);
		SXSSFCell cell5 = headerRow3.createCell(3);
		cell5.setCellValue(reportName);
		cell5.setCellStyle(headStyle);

		SXSSFCell cell6 = headerRow3.createCell(7);
		cell6.setCellValue("Date :" + dateStr);
		cell6.setCellStyle(headStyle);

		currentRow = currentRow + 5;
		for (String col : colNames) {

			XSSFRichTextString text = new XSSFRichTextString(col);
			SXSSFCell cell = headerRow.createCell(currentColumn);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(text);
			currentColumn++;

		}
		// POPULATE VALUE ROWS/COLUMNS
		currentRow++;// exclude header
		// create header row

		for (int i = 0; i < totalResults.size(); i++) {
			SXSSFRow row = sheet.createRow(currentRow);
			int countrow = 0;
			for (Object ob : totalResults.get(i)) {
				row.createCell(countrow).setCellValue(ob != null ? ob.toString() : "");
				countrow++;
			}
			currentRow++;
		}

	}

	public static void buildExcelDocument1(List<List<String>> totalResults, Set<String> colNames, SXSSFWorkbook wb,
			HttpServletRequest request, HttpServletResponse response, String reportName, EmployeeBO employee,
			String dateStr) throws Exception {

		// change the file name

		// create excel xls sheet
		SXSSFSheet sheet = wb.createSheet(reportName);
		sheet.setDefaultColumnWidth((short) 12);
		int currentRow = 0;
		short currentColumn = 0;
		// CREATE STYLE FOR HEADER
		XSSFCellStyle headerStyle = (XSSFCellStyle) wb.createCellStyle();
		XSSFFont headerFont = (XSSFFont) wb.createFont();
		headerFont.setBold(true);
		headerStyle.setFont(headerFont);

		XSSFCellStyle headStyle = (XSSFCellStyle) wb.createCellStyle();
		XSSFFont headFont = (XSSFFont) wb.createFont();
		headFont.setBold(true);
		headFont.setColor(IndexedColors.ORANGE.getIndex());
		headStyle.setFont(headFont);
		// POPULATE HEADER COLUMNS
		SXSSFRow headerRow = sheet.createRow(5);

		// create style for header cells
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("Arial");
		/* style.setFillForegroundColor(XSSFColor.toXSSFColor(org.apa); */
		/* font.setColor(HSSFColor.WHITE.index); */
		style.setFont(font);
		/*
		 * InputStream logo = new
		 * FileInputStream("/home/fmsreports/reports/images/logo_scil1.png");
		 */

		String respath = "/static/assets/logo_scil1.png";
		InputStream logo = ExcelView.class.getResourceAsStream(respath);
		if (logo == null)
			throw new Exception("resource not found: " + respath);
		/* Read the input image into InputStream */
		/* Convert Image to byte array */
		byte[] bytes = IOUtils.toByteArray(logo);
		/* Add Picture to workbook and get a index for the picture */
		int my_picture_id = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
		/* Close Input Stream */
		logo.close();
		/* Create an anchor point */
		ClientAnchor my_anchor = new XSSFClientAnchor();
		/*
		 * Define top left corner, and we can resize picture suitable from there
		 */
		my_anchor.setCol1(1);
		my_anchor.setRow1(1);
		SXSSFDrawing drawing = sheet.createDrawingPatriarch();
		SXSSFPicture my_picture = drawing.createPicture(my_anchor, my_picture_id);
		my_picture.resize();
		SXSSFRow headerRow1 = sheet.createRow(1);
		SXSSFCell cell1 = headerRow1.createCell(3);
		cell1.setCellValue("Sri Chaitanya Educational Institutions");
		cell1.setCellStyle(headStyle);

		SXSSFCell cell2 = headerRow1.createCell(7);
		cell2.setCellValue("HYDERABAD");
		cell2.setCellStyle(headStyle);

		SXSSFRow headerRow2 = sheet.createRow(2);
		SXSSFCell cell3 = headerRow2.createCell(2);
		cell3.setCellValue("(Admin. by Sri Kalyana Chakravarti Memorial Educational Trust)");
		cell3.setCellStyle(headStyle);

		SXSSFCell cell4 = headerRow2.createCell(7);
		if (employee != null) {
			cell4.setCellValue("" + employee.getCampus());
		} else {
			cell4.setCellValue("");
		}

		cell4.setCellStyle(headStyle);

		SXSSFRow headerRow3 = sheet.createRow(3);
		SXSSFCell cell5 = headerRow3.createCell(3);
		cell5.setCellValue(reportName);
		cell5.setCellStyle(headStyle);

		SXSSFCell cell6 = headerRow3.createCell(7);
		cell6.setCellValue("Date :" + dateStr);
		cell6.setCellStyle(headStyle);

		currentRow = currentRow + 5;
		for (String col : colNames) {

			XSSFRichTextString text = new XSSFRichTextString(col);
			SXSSFCell cell = headerRow.createCell(currentColumn);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(text);
			currentColumn++;

		}
		// POPULATE VALUE ROWS/COLUMNS
		currentRow++;// exclude header
		// create header row

		for (int i = 0; i < totalResults.size(); i++) {
			SXSSFRow row = sheet.createRow(currentRow);
			int countrow = 0;
			for (String ob : totalResults.get(i)) {
				row.createCell(countrow).setCellValue(ob != null ? ob.toString() : "");
				countrow++;
			}
			currentRow++;
		}

	}

}
