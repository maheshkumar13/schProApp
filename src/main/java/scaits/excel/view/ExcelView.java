package scaits.excel.view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
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
import scaits.bo.student.ClassSectionBO;
import scaits.bo.student.StudyClassBO;
import scaits.bo.student.SubjectBO;

public class ExcelView {

	public static void buildExcelDocument(List<Object[]> totalResults, Set<String> colNames, SXSSFWorkbook wb,
			HttpServletRequest request, HttpServletResponse response, String reportName, SubjectBO subject,
			String dateStr, ClassSectionBO section, StudyClassBO studyClass, long hightMarks, double percenage) throws Exception {

		// change the file name

		// create excel xls sheet
		SXSSFSheet sheet = wb.createSheet(reportName);

		XSSFCellStyle style5 = (XSSFCellStyle) wb.createCellStyle();
		style5.setBorderBottom(BorderStyle.THIN);
		style5.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style5.setBorderLeft(BorderStyle.THIN);
		style5.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style5.setBorderRight(BorderStyle.THIN);
		style5.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style5.setBorderTop(BorderStyle.THIN);
		style5.setTopBorderColor(IndexedColors.BLACK.getIndex());

		XSSFCellStyle style2 = (XSSFCellStyle) wb.createCellStyle();
		style2.setAlignment(HorizontalAlignment.CENTER);
		style2.setVerticalAlignment(VerticalAlignment.CENTER);

		SXSSFRow row = sheet.createRow(1);

		SXSSFCell localXSSFCell = row.createCell(0);
		localXSSFCell.setCellValue("AWARD LIST");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);
		// Create a cellRangeAddress to select a range to merge.
		CellRangeAddress cellRangeAddress2 = new CellRangeAddress(1, 1, 0, 6);

		sheet.addMergedRegion(cellRangeAddress2);
		// Add a row and cell and some text in it.

		row = sheet.createRow((short) 2);
		localXSSFCell = row.createCell(0);
		localXSSFCell.setCellValue("PERIODIC TEST 20   20  ");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);
		// Create a cellRangeAddress to select a range to merge.
		cellRangeAddress2 = new CellRangeAddress(2, 2, 0, 6);

		sheet.addMergedRegion(cellRangeAddress2);

		row = sheet.createRow((short) 3);
		localXSSFCell = row.createCell(0);
		localXSSFCell.setCellValue("CLASS:"+ studyClass.getDisplayName()       +" &SEC:" +section.getSectionName());
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		localXSSFCell = row.createCell(5);
		localXSSFCell.setCellValue("MAXIMUM MAKRS : 40" );
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		row = sheet.createRow((short) 4);
		localXSSFCell = row.createCell(0);
		localXSSFCell.setCellValue("SUBJECT:" + subject.getSubjectName());
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		localXSSFCell = row.createCell(5);
		localXSSFCell.setCellValue("PASS MARKS : 10");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		int currentRow = 0;
		short currentColumn = 0;
		SXSSFRow headerRow = sheet.createRow(5);
		XSSFCellStyle headerStyle = (XSSFCellStyle) wb.createCellStyle();

		currentRow = currentRow + 5;
		List<String> aList = new ArrayList<String>(colNames);
		for (int i = 0; i < 7; i++) {
			XSSFRichTextString text = null;

			if (i == 2 || i == 4 || i == 6) {
				text = new XSSFRichTextString("");
			} else if (i == 3) {
				text = new XSSFRichTextString(aList.get(i - 1));
			} else if (i == 5) {
				text = new XSSFRichTextString(aList.get(i - 2));
			} else {
				text = new XSSFRichTextString(aList.get(i));
			}

			SXSSFCell cell = headerRow.createCell(currentColumn);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(text);
			cell.setCellStyle(style5);
			currentColumn++;

		}

		// POPULATE VALUE ROWS/COLUMNS
		currentRow++;// exclude header

		RegionUtil.setBorderBottom(BorderStyle.THIN, CellRangeAddress.valueOf("A5:G5"), sheet);
		RegionUtil.setBorderTop(BorderStyle.THIN, CellRangeAddress.valueOf("A2:G2"), sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, CellRangeAddress.valueOf("G2:G6"), sheet);
		RegionUtil.setBorderLeft(BorderStyle.THIN, CellRangeAddress.valueOf("A2:A5"), sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, CellRangeAddress.valueOf("A6:A8"), sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, CellRangeAddress.valueOf("B6:B8"), sheet);

		sheet.setDefaultColumnWidth((short) 12);
		// CREATE STYLE FOR HEADER
		XSSFFont headerFont = (XSSFFont) wb.createFont();
		headerFont.setBold(true);
		headerStyle.setFont(headerFont);

		XSSFCellStyle headStyle = (XSSFCellStyle) wb.createCellStyle();
		XSSFFont headFont = (XSSFFont) wb.createFont();
		headFont.setBold(true);
		headFont.setColor(IndexedColors.ORANGE.getIndex());
		headStyle.setFont(headFont);

		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		headStyle.setBorderRight(BorderStyle.THIN);
		headStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

		for (int i = 0; i < totalResults.size(); i++) {
			SXSSFRow rows = sheet.createRow(currentRow);
			int countrow = 0;
			List list = Arrays.asList(totalResults.get(i));
			for (Object ob : totalResults.get(i)) {
				if (countrow == 0 || countrow == 1) {
					SXSSFCell cell = rows.createCell((short) countrow);
					cell.setCellValue(ob != null ? ob.toString() : "");
					cell.setCellStyle(style5);

				}
				if (countrow == 3) {
					SXSSFCell cell = rows.createCell((short) countrow);
					cell.setCellValue(list.get(2) != null ? list.get(2).toString() : "");
					cell.setCellStyle(style5);

				}
				if (countrow == 5) {
					SXSSFCell cell = rows.createCell((short) countrow);
					cell.setCellValue(list.get(3) != null ? list.get(3).toString() : "");
					cell.setCellStyle(style5);

				}

				if (countrow == 2 || countrow == 4 || countrow == 6) {
					SXSSFCell cell = rows.createCell((short) countrow);
					cell.setCellValue("");
					cell.setCellStyle(style5);
				}
				countrow++;
			}
			currentRow++;
		}

		int k = 5;
		int m = 1;
		for (int i = 0; i <= totalResults.size(); i++) {

			CellRangeAddress cellRangeAddress21 = new CellRangeAddress(k, k, m, m + 1);

			sheet.addMergedRegion(cellRangeAddress21);

			CellRangeAddress cellRangeAddress22 = new CellRangeAddress(k, k, m + 2, m + 3);

			sheet.addMergedRegion(cellRangeAddress22);

			CellRangeAddress cellRangeAddress23 = new CellRangeAddress(k, k, m + 4, m + 5);

			sheet.addMergedRegion(cellRangeAddress23);

			k++;

		}

		currentRow = currentRow + 2;

		row = sheet.createRow((short) currentRow);
		localXSSFCell = row.createCell(0);
		localXSSFCell.setCellValue("HIGHEST MARKS:" +hightMarks);
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		localXSSFCell = row.createCell(5);
		localXSSFCell.setCellValue("PASS PERCENTAGE :" +percenage);
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		row = sheet.createRow((short) currentRow + 1);
		localXSSFCell = row.createCell(0);
		localXSSFCell.setCellValue("EXAMINER:");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		localXSSFCell = row.createCell(3);
		localXSSFCell.setCellValue("CHECKER :");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		localXSSFCell = row.createCell(5);
		localXSSFCell.setCellValue("HEADMISTREE :");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

	}

	public static void buildExcelDocumentHalf(List<Object[]> totalResults, Set<String> colNames, SXSSFWorkbook wb,
			HttpServletRequest request, HttpServletResponse response, String reportName, SubjectBO subject,
			String dateStr) throws Exception {

		// change the file name

		// create excel xls sheet
		SXSSFSheet sheet = wb.createSheet(reportName);

		XSSFCellStyle style5 = (XSSFCellStyle) wb.createCellStyle();
		style5.setBorderBottom(BorderStyle.THIN);
		style5.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style5.setBorderLeft(BorderStyle.THIN);
		style5.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style5.setBorderRight(BorderStyle.THIN);
		style5.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style5.setBorderTop(BorderStyle.THIN);
		style5.setTopBorderColor(IndexedColors.BLACK.getIndex());

		XSSFCellStyle style2 = (XSSFCellStyle) wb.createCellStyle();
		style2.setAlignment(HorizontalAlignment.CENTER);
		style2.setVerticalAlignment(VerticalAlignment.CENTER);

		SXSSFRow row = sheet.createRow(1);

		SXSSFCell localXSSFCell = row.createCell(0);
		localXSSFCell.setCellValue("AWARD LIST");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);
		// Create a cellRangeAddress to select a range to merge.
		CellRangeAddress cellRangeAddress2 = new CellRangeAddress(1, 1, 0, 6);

		sheet.addMergedRegion(cellRangeAddress2);
		// Add a row and cell and some text in it.

		row = sheet.createRow((short) 2);
		localXSSFCell = row.createCell(0);
		localXSSFCell.setCellValue("HALF YEARlY EXAMS20   20");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);
		// Create a cellRangeAddress to select a range to merge.
		cellRangeAddress2 = new CellRangeAddress(2, 2, 0, 6);

		sheet.addMergedRegion(cellRangeAddress2);

		row = sheet.createRow((short) 3);
		localXSSFCell = row.createCell(0);
		localXSSFCell.setCellValue("CLASS:        &SEC:");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		localXSSFCell = row.createCell(5);
		localXSSFCell.setCellValue("MAXIMUM MAKRS :");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		row = sheet.createRow((short) 4);
		localXSSFCell = row.createCell(0);
		localXSSFCell.setCellValue("SUBJECT:" +subject.getSubjectName());
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		localXSSFCell = row.createCell(5);
		localXSSFCell.setCellValue("PASS MARKS :");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		row = sheet.createRow((short) 5);
		localXSSFCell = row.createCell(0);
		localXSSFCell.setCellValue("ADMISSION NO");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);
		// Create a cellRangeAddress to select a range to merge.
		CellRangeAddress cellRangeAddress = new CellRangeAddress(5, 7, 0, 0);

		// Merge the selected cells.
		sheet.addMergedRegion(cellRangeAddress);

		localXSSFCell = row.createCell(1);
		localXSSFCell.setCellValue("Name Of Student");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);
		// Create a cellRangeAddress to select a range to merge.
		CellRangeAddress cellRangeAddress1 = new CellRangeAddress(5, 7, 1, 1);

		// Merge the selected cells.
		sheet.addMergedRegion(cellRangeAddress1);
		localXSSFCell = row.createCell(2);
		localXSSFCell.setCellValue("Marks Obtained");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);
		// Create a cellRangeAddress to select a range to merge.
		cellRangeAddress1 = new CellRangeAddress(5, 5, 2, 6);

		// Merge the selected cells.
		sheet.addMergedRegion(cellRangeAddress1);
		row = sheet.createRow((short) 6);
		localXSSFCell = row.createCell((short) 2);
		localXSSFCell.setCellValue("PERIODIC TEST");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell = row.createCell((short) 3);
		localXSSFCell.setCellValue("NOTE BOOK");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell = row.createCell((short) 4);
		localXSSFCell.setCellValue("PROJECT");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell = row.createCell((short) 5);
		localXSSFCell.setCellValue(subject.getSubjectName());
		localXSSFCell.setCellStyle(style5);
		localXSSFCell = row.createCell((short) 6);
		localXSSFCell.setCellValue("TOTAL");
		localXSSFCell.setCellStyle(style5);

		row = sheet.createRow((short) 7);
		localXSSFCell = row.createCell((short) 2);
		localXSSFCell.setCellValue("10");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell = row.createCell((short) 3);
		localXSSFCell.setCellValue("5");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell = row.createCell((short) 4);
		localXSSFCell.setCellValue("5");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell = row.createCell((short) 5);
		localXSSFCell.setCellValue("80");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell = row.createCell((short) 6);
		localXSSFCell.setCellValue("100");

		localXSSFCell.setCellStyle(style5);

		RegionUtil.setBorderBottom(BorderStyle.THIN, CellRangeAddress.valueOf("A5:G5"), sheet);
		RegionUtil.setBorderTop(BorderStyle.THIN, CellRangeAddress.valueOf("A2:G2"), sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, CellRangeAddress.valueOf("G2:G6"), sheet);
		RegionUtil.setBorderLeft(BorderStyle.THIN, CellRangeAddress.valueOf("A2:A5"), sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, CellRangeAddress.valueOf("A6:A8"), sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, CellRangeAddress.valueOf("B6:B8"), sheet);

		sheet.setDefaultColumnWidth((short) 12);
		int currentRow = 0;
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

		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		headStyle.setBorderRight(BorderStyle.THIN);
		headStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

		currentRow = currentRow + 7;
		// POPULATE VALUE ROWS/COLUMNS
		currentRow++;// exclude header
		// create header row

		for (int i = 0; i < totalResults.size(); i++) {
			SXSSFRow rows = sheet.createRow(currentRow);
			int countrow = 0;
			for (Object ob : totalResults.get(i)) {
				if (countrow <= 6) {
					SXSSFCell cell = rows.createCell((short) countrow);
					cell.setCellValue(ob != null ? ob.toString() : "");
					cell.setCellStyle(style5);
					countrow++;
				}

			}
			currentRow++;
		}

		currentRow = currentRow+2;

		row = sheet.createRow((short) currentRow);
		localXSSFCell = row.createCell(0);
		localXSSFCell.setCellValue("HIGHEST MARKS:");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		localXSSFCell = row.createCell(5);
		localXSSFCell.setCellValue("PASS PERCENTAGE :");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		row = sheet.createRow((short) currentRow + 1);
		localXSSFCell = row.createCell(0);
		localXSSFCell.setCellValue("EXAMINER:");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		localXSSFCell = row.createCell(3);
		localXSSFCell.setCellValue("CHECKER :");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

		localXSSFCell = row.createCell(5);
		localXSSFCell.setCellValue("HEADMISTREE :");
		localXSSFCell.setCellStyle(style5);
		localXSSFCell.setCellStyle(style2);

	}

	public static void buildExcelDocumentpain(List<Object[]> totalResults, Set<String> colNames, SXSSFWorkbook wb,
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
		SXSSFRow headerRow = sheet.createRow(0);

		// create style for header cells
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("Arial");
		/* style.setFillForegroundColor(XSSFColor.toXSSFColor(org.apa); */
		/* font.setColor(HSSFColor.WHITE.index); */
		style.setFont(font);

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
			cell4.setCellValue("" + employee.getCampus() + "," + employee.getCampus());
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
