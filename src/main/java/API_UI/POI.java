package API_UI;

import java.io.*;
import  org.apache.poi.hssf.usermodel.HSSFSheet;  
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import  org.apache.poi.hssf.usermodel.HSSFRow;  

public class POI {
	static String filename = "C:\\Users\\DELL\\Desktop\\TestData\\Spaces\\S.csv";

//	public static void CreateFile() throws IOException {
//		
//			//create csv file 
//			FileOutputStream fileOut = new FileOutputStream(filename);
//			fileOut.close();
//			System.out.println("Excel file has been generated successfully.");
//		
//	}

	public static void Insertdata() throws IOException {
		//creating an instance of HSSFWorkbook class  
		HSSFWorkbook workbook = new HSSFWorkbook();  
		
		//invoking creatSheet() method and passing the name of the sheet to be created   
		HSSFSheet sheet = workbook.createSheet("Space_C");   
		//creating the 0th row using the createRow() method  
		HSSFRow rowhead = sheet.createRow((short)0);  
		
		//creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
		rowhead.createCell(0).setCellValue("exid");  
		rowhead.createCell(1).setCellValue("key");  
		rowhead.createCell(2).setCellValue("table");  
		
		for(int j = 1; j < 5; j++)
		{
//			int lastrow = sheet.getPhysicalNumberOfRows();
//			System.out.println("Lastrow" + lastrow);
			HSSFRow row = sheet.createRow((short)j);  
			row.createCell(0).setCellValue("1");  
			row.createCell(1).setCellValue("John William");  
			row.createCell(2).setCellValue("9999999");  
		}
		FileOutputStream fileOut = new FileOutputStream(filename); 
		workbook.write(fileOut);  
		//closing the Stream  
		fileOut.close();  
		//closing the workbook  
		workbook.close();  
		//prints the message on the console  
		System.out.println("Excel file has been generated and insert data successfully.");  
		}   
	
	public static void main(String args[]) throws IOException
	{
//		CreateFile();
		Insertdata();
	}
}
