package utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelutil {

	public static XSSFSheet sheet;
	public static XSSFWorkbook workbook;

	public excelutil(String excelPath) {

		try {
			workbook = new XSSFWorkbook(excelPath);
			// sheet = workbook.getSheet(sheetName);
			sheet = workbook.getSheetAt(0);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}

	public int getRowCount(String sheetName) {

		int index = workbook.getSheetIndex(sheetName);
		int rowCount = 0;
		try {
			sheet = workbook.getSheetAt(index);
			rowCount = sheet.getPhysicalNumberOfRows();
			// System.out.println("No. of rows: "+rowCount);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return rowCount;
	}

	public int getColCount(String sheetName) {

		int index = workbook.getSheetIndex(sheetName);
		int colCount = 0;
		try {
			sheet = workbook.getSheetAt(index);
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			// System.out.println("No. of rows: "+rowCount);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return colCount;
	}

	public String getCelldata(String sheetName, int rowNum, int colNum) {

		int index = workbook.getSheetIndex(sheetName);
		String cellData = null;
		try {
			sheet = workbook.getSheetAt(index);
			cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			// System.out.println(cellData);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return cellData;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// getRowCount();
		// getCelldata(0,0);

	}

}
