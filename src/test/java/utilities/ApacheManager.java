package utilities;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApacheManager {
	private static String[][] excelData = new String[3][4];
	public static String[][] readData() {
		try {
			String directoryPath = System.getProperty("user.dir");
			String relativePath = "/src/test/resources/data/data.xlsx";
			
			FileInputStream inputStream = new FileInputStream(directoryPath + relativePath);
			
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			Boolean isFirstRow = true;
			int i = -1;
			int j = 0;
			while(rowIterator.hasNext()) {
				if(isFirstRow) {
					isFirstRow = false;
					continue;
				}
				Row row = rowIterator.next();
				
				Iterator<Cell> cellIterator = row.iterator();
				i++;
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					excelData[i][j++] = cell.toString();
				}
				
			}
			
			workbook.close();
			inputStream.close();
			
			return excelData;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return new String[0][0];
		}
		
	}
}
 