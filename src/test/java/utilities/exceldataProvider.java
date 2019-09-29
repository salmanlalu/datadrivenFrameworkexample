package utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import base.testBase;

public class exceldataProvider extends testBase {

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {

		String sheetname = m.getName();
		System.out.println(sheetname);
		Object data[][] = getDatafromsheet(sheetname);
		return data;

	}

	public static Object[][] getDatafromsheet(String sheetName) {

		int rowCount = excel.getRowCount(sheetName);
		int colCount = excel.getColCount(sheetName);

		Object data[][] = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {

				String cellData = excel.getCelldata(sheetName, i, j);
				System.out.print(cellData + " | ");
				data[i - 1][j] = cellData;
			}

			System.out.println();

		}

		return data;
	}

}
