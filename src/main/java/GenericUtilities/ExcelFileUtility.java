package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 *@author k.sreeja this method is used to work with Excel file 
 */
public class ExcelFileUtility {
	
	public Workbook wb;
	/**
	 * this method is used to fetch the data from excel
	 * @param sheetname
	 * @param rowindex
	 * @param cellindex
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
public String fetchDataFromExcel(String sheetname,int rowindex,int cellindex) throws EncryptedDocumentException, IOException {
	FileInputStream fis = new FileInputStream("./src/test/resources/AdvSelExcel.xlsx");
	 wb = WorkbookFactory.create(fis);
	 	 String data = wb.getSheet(sheetname).getRow(rowindex).getCell(cellindex).toString();
	 	 return data;
}	
/**
 * this method is used to write back data to new row in excel
 * @param sheetname
 * @param rowindex
 * @param cellindex
 * @param data
 * @throws EncryptedDocumentException
 * @throws IOException
 */
public void writeBackDataToExcelNewRow(String sheetname,int rowindex,int cellindex,String data) throws EncryptedDocumentException, IOException {	
	FileInputStream fis = new FileInputStream("./src/test/resources/AdvSelExcel.xlsx");
	 wb = WorkbookFactory.create(fis);
	Sheet sh = wb.getSheet(sheetname);
		 sh.createRow(rowindex).createCell(cellindex).setCellValue(data);
		 FileOutputStream fos = new FileOutputStream("./src/test/resources/AdvSelExcel.xlsx");
		 wb.write(fos);
}
/**
 * this method is used to write back data to existing row in excel
 * @param sheetname
 * @param rowindex
 * @param cellindex
 * @param data
 * @throws EncryptedDocumentException
 * @throws IOException
 */
public void writeBackDataToExcelExsistingRow(String sheetname,int rowindex,int cellindex,String data) throws EncryptedDocumentException, IOException {	
	FileInputStream fis = new FileInputStream("./src/test/resources/AdvSelExcel.xlsx");
	 wb = WorkbookFactory.create(fis);
	Sheet sh = wb.getSheet(sheetname);
		 sh.getRow(rowindex).createCell(cellindex).setCellValue(data);
		 FileOutputStream fos = new FileOutputStream("./src/test/resources/AdvSelExcel.xlsx");
		 wb.write(fos);
}
/**
 * this method is used to close the excel
 */
public void CloseTheExcel() throws IOException {
	wb.close();
}
}
