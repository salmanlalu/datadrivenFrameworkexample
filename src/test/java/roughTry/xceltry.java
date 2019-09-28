package roughTry;
import base.*;

public class xceltry  extends testBase {

	public static void main(String[] args) {
		
//		String data = excel.getCelldata(0, 0);
//		System.out.println(data);
		
		String sheetName = "addCustomerTest";
		String sheetName1 = "openAccountTest";
		
		int rowCount = excel.getRowCount(sheetName1);
		int colCount = excel.getColCount(sheetName1);
		
		for (int i=1; i<rowCount; i++) {
			for(int j=0; j<colCount; j++) {
				
				String cellData = excel.getCelldata(sheetName1,i,j);
				System.out.print(cellData+" | ");
			}
			
			System.out.println();
			
		}
		

	}

}
