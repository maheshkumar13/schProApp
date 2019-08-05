package scaits.excel.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

@Component
public class ExcelUtil {

	public static List<Map<Integer, List<Object>>> readAllExcelSheetsData(InputStream inputStream)
	        throws InvalidFormatException, IOException {
		Workbook workbook = WorkbookFactory.create(inputStream);

		int numberOfSheets = workbook.getNumberOfSheets();
		List<Map<Integer, List<Object>>> sheets = new ArrayList<Map<Integer, List<Object>>>();

		for (int i = 0; i < numberOfSheets; i++) {
			// Get the nth sheet from the workbook
			Sheet sheet = workbook.getSheetAt(i);
			// every sheet has rows, iterate over them
			Map<Integer, List<Object>> rows = new LinkedHashMap<Integer, List<Object>>();
			Iterator<Row> rowIterator = sheet.iterator();
			int rowCnt = 0;
			while (rowIterator.hasNext()) {
				rowCnt++;
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				List<Object> cells = new ArrayList<Object>();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellTypeEnum()) {
					case STRING:
						cells.add(cell.getStringCellValue());
						break;
					case NUMERIC:
						cells.add((int)cell. getNumericCellValue());
						break;
					case BOOLEAN:
						cells.add(cell.getBooleanCellValue());
						break;
					case FORMULA:
						cells.add(cell.getCellFormula());
						break;
					case BLANK:
						break;
					case ERROR:
						cells.add(cell.getErrorCellValue());
						break;
					case _NONE:
						break;
					default:
						break;
					}
				}
				rows.put(rowCnt,
				         cells);
			}
			sheets.add(rows);
		}

		return sheets;
	}

	public static Map<Integer, List<Object>> readExcelSheetData(InputStream inputStream, int sheetNo)
	        throws InvalidFormatException, IOException {
		Workbook workbook = WorkbookFactory.create(inputStream);

		Map<Integer, List<Object>> rows = new LinkedHashMap<Integer, List<Object>>();

		// Get the nth sheet from the workbook
		Sheet sheet = workbook.getSheetAt(sheetNo);
		// every sheet has rows, iterate over them
		Iterator<Row> rowIterator = sheet.iterator();
		int rowCnt = 0;
		while (rowIterator.hasNext()) {
			rowCnt++;
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			List<Object> cells = new ArrayList<Object>();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getCellTypeEnum()) {
				case STRING:
					cells.add(cell.getStringCellValue());
					break;
				case NUMERIC:
					cells.add(cell.getNumericCellValue());
					break;
				case BOOLEAN:
					cells.add(cell.getBooleanCellValue());
					break;
				case FORMULA:
					cells.add(cell.getCellFormula());
					break;
				case BLANK:
					break;
				case ERROR:
					cells.add(cell.getErrorCellValue());
					break;
				case _NONE:
					break;
				default:
					break;
				}
			}
			rows.put(rowCnt,
			         cells);
		}

		return rows;
	}
}
