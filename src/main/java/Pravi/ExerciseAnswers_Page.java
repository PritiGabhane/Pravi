package Pravi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
//import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExerciseAnswers_Page 
{
	WebDriver driver;
	String ExerciseAnswers, ExerciseQuestions, EID, answer, type, Answer, Type, URL, UserName, Password, Query;
	int key, Question, j = 0, k = 1, l = 1, m = 1;
	ExerciseID_Page EI;
	WebElement Radio_Answers, Checkbox_Answers, NumberText_Answers;
	HashMap<Integer, String[]> APIAnswers = new HashMap<>();

	@FindBy(xpath = "//label[@class = 'form-label']") List<WebElement> Exercise_Questions;

	public ExerciseAnswers_Page(WebDriver driver)
	{
		//Call the webdriver
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}

	//@Test
	public void API_Answers() throws SQLException   
	{
		EI = new ExerciseID_Page(driver);
		EID = EI.Exercise_Id();
		System.out.println(EID);
		
		URL = "jdbc:postgresql://pravinyam-dev-do-user-9198634-0.b.db.ondigitalocean.com:25060/defaultdb";
		UserName = "qauser";
		Password = "priti@123pass$";

		//Establish connection
		Connection con = DriverManager.getConnection(URL, UserName, Password);

		//Create statement object
		Statement stmt = con.createStatement();

		//Execute Query
		Query = "SELECT key, answer, type FROM pravinyam.cqadata where exid = '"+EID+"' ORDER BY exid ASC ";

		//Read and store data
		ResultSet result = stmt.executeQuery(Query);

		while(result.next())
		{
			//Get question number
			key = result.getInt("key");
			
			//Get answer
			answer = result.getString("answer");
			
			//Get type of the answer
			type = result.getString("type");

			//Give to answer UI
			APIAnswers.put(key, new String[]{answer, type});
			System.out.println("Answers from database = " + key + " - " + answer + " - " + type);
		}

		//Close connection
		con.close();
		System.out.println("Connection closed");
	}

	public void Answers_UI() throws SQLException
	{
		for (int i : APIAnswers.keySet()) 
		{
			//Get Question no from UI
			ExerciseQuestions = Exercise_Questions.get(j).getText();
			Question = Integer.parseInt(ExerciseQuestions.substring(0, 1));

			//Get API answers and type from database
			Answer = APIAnswers.get(i)[0];
			Type = APIAnswers.get(i)[1];
			System.out.println("Answers to UI = " + Question + " - " + Answer + " - " + Type);

			//Send answer to UI
			if(Type.equals("number") || Type.equals("text"))
			{	
				NumberText_Answers = driver.findElement(By.xpath("(//input[@class = 'input'])["+ k +"]"));
				NumberText_Answers.sendKeys(Answer);
				k++;
			}
			else if(Type.equals("radio"))
			{
				Radio_Answers = driver.findElement(By.xpath("(//div[@class = 'form-group-radio'])["+ l +"]//input[@type = 'radio' and @value = "+ Answer +"]"));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();", Radio_Answers);
				new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.visibilityOf(Radio_Answers));
				new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(Radio_Answers)).click();
				l++;
			}
			else if(Type.equals("checkbox"))
			{
				for(int ch = 0; ch < Answer.length(); ch++)
				{
					if(Character.isDigit(Answer.charAt(ch)))
					{
						System.out.print("length and character = " + Answer.length() + " " + Answer.charAt(ch) + "\n");
						Checkbox_Answers = driver.findElement(By.xpath("(//div[@class = 'form-group-checkbox'])["+ m +"]//input[@type = 'checkbox' and @value= "+ Answer.charAt(ch) +"]"));
						((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();", Checkbox_Answers);
						new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.visibilityOf(Checkbox_Answers));
						new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(Checkbox_Answers)).click();
					}
				}
				m++;
			}
			j++;
		}

		System.out.println("Exercise is executed");
	}	

}


