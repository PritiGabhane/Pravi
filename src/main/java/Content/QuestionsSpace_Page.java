package Content;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Pravi.*;

public class QuestionsSpace_Page {
	WebDriver driver;
	String Question, ExerciseQuestion, EID, ExerciseName;
	int QuestionNo;
	ExerciseID_Page EI;
	HSSFWorkbook workbook; HSSFSheet sheet, sheet1, sheet2, sheet3, sheet4, sheet5, sheet6; HSSFRow row;
    String filename = "C:\\Users\\DELL\\Desktop\\TestData\\Spaces\\Spaces_Questions.csv";
					
	@FindBy(xpath = "//label[@class = 'form-label']")
	List<WebElement> Exercise_Questions;

	@FindBy(xpath = "//h3[@class = 'editor-title']")
	WebElement Exercise;

	public QuestionsSpace_Page(WebDriver driver) {
		// Call the webdriver
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void Question_Space() throws Exception {
		// Get Exercise Id from UI
		EI = new ExerciseID_Page(driver);
		EID = EI.Exercise_Id();

		// Get Exercise Name Pravi
		//		ExerciseName = Exercise.getText();

		for (int i = 0; i < Exercise_Questions.size(); i++) {
			// Get Question from UI
			ExerciseQuestion = Exercise_Questions.get(i).getText();

			// Get Question number
			QuestionNo = Integer.parseInt(ExerciseQuestion.substring(0, 1));
			Question = ExerciseQuestion.substring(3);

			// Check the space before questionmark
			char[] array = ExerciseQuestion.toCharArray();
			int length = ExerciseQuestion.length();
			for (int j = 0; j < length - 1; j++) {
				// check the spaces before questionmark
				if (array[j] == ' ' && array[j + 1] == '?') {
					System.out.println(EID + " --> " + QuestionNo + " --> " + Question);
					
					//insert data in the excel sheet
//					InsertData();
				}
			}
//			System.out.println(Question + " --> " + "Correct Question!!!");			
		}
	}

	public void CreateFile() throws IOException 
	{
		//creating an instance of HSSFWorkbook class  
		workbook = new HSSFWorkbook();  

		//invoking creatSheet() method and passing the name of the sheet to be created   
		sheet1 = workbook.createSheet("Space_C");   
		sheet2 = workbook.createSheet("Space_PY");
		sheet3 = workbook.createSheet("Space_VP");
		sheet4 = workbook.createSheet("Space_JS");
		sheet5 = workbook.createSheet("Space_BE");
		sheet6 = workbook.createSheet("Space_PS");

		//closing the workbook  
		workbook.close();  
		
		//prints the message on the console  
		System.out.println("Excel file and sheets are has been generated");  
	}
	
	public void InsertData() throws IOException
	{
		//read the file
		FileInputStream fileIn = new FileInputStream(filename); 
		workbook =  new HSSFWorkbook(fileIn);  

		sheet = workbook.getSheet("Space_PS");
	
		int RowNo = sheet.getPhysicalNumberOfRows();
		System.out.println("Row Number - " + RowNo);
		
		//creat object for row
		row = sheet.createRow((short) RowNo);
		
		if(RowNo == 0)
		{
			//creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
			row.createCell(0).setCellValue("exid");  
			row.createCell(1).setCellValue("key");  
			row.createCell(2).setCellValue("table");
		}
		else
		{
			//to fetch data in the rows
			row.createCell(0).setCellValue(EID);
			row.createCell(1).setCellValue(QuestionNo);
			row.createCell(2).setCellValue(Question);
		}
		
		//to write file
		FileOutputStream fileOut = new FileOutputStream(filename); 
		workbook.write(fileOut);
		
		//closing the Stream  
		fileOut.close();  
		
		//closing the workbook  
		workbook.close();  
	}
}
