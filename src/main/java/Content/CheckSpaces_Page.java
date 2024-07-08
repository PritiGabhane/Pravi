package Content;

import java.time.Duration;
import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import Pravi.*;

public class CheckSpaces_Page {
	int Nvar = 1, Pvar = 1, cnt = 0;
	String sizeAvailable, Category, EID;
	WebDriver driver;
	ExerciseID_Page EI; QuestionsSpace_Page QS;
	
	@FindBy(xpath = "//*[@href='/tracks/Modules']") WebElement Module;
	
	@FindBy(xpath = "//nav[@class='pyui_exercises-status-tabs']//ul//li") List<WebElement> UserAction_list;

	@FindBy(xpath = "//a[text() = '1']") WebElement First;

	@FindBy(xpath = "(//a[@href = '/tracks/Modules/Exercises/Editor'])[1]") WebElement Exercise;

	@FindBy(xpath = "//span[text()='Next']") WebElement Next;

	@FindBy(xpath = "//span[text()='Previous']") WebElement Previous;

	@FindBy(xpath = "//label[@class = 'form-label']") WebElement Label;
	
	@FindBy(xpath = "//div[@class= 'problem-description']") WebElement Description;
	
	@FindBy(xpath = "//div[@class = 'pyui_code-viewer-questions questions']") WebElement MissingValue;

	public CheckSpaces_Page(WebDriver driver)
	{
		//Call the webdriver
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//@Test
	public void UserAction(String Action) throws Exception 
	{
		//Select the UserAction
		System.out.println("UserAction values are : " + UserAction_list.size());
		for(int i=0; i<UserAction_list.size(); i++)
		{     
			if(UserAction_list.get(i).getText().contains(Action))
			{
			Category = UserAction_list.get(i).getText();
			System.out.println("Selected Action : " +Category);
			UserAction_list.get(i).click();
			//VisitNext();
			}
		}
	}

	//@Test
	public int CountExercises()
	{	
		//Count the exercises in useraction
		cnt = Integer.parseInt(Category.replaceAll("[^0-9]", ""));	
		System.out.println("Total of available exercises : " +cnt);
		return cnt;
	}

	//@Test
	public void FirstExercise() throws Exception
	{
		//Open the first exercise and Check answers
		First.click();
		Exercise.click();
//		VisitExercise_method();
	}

	//@Test
	public void VisitNext(String ModuleUI) throws Exception
	{   
		//call to visit method 
		VisitExercise_method();
		
		//Check answers for all exercises
		for(int i=0; i<cnt-1; i++)
		{
			//Get exercise id from UI
			EI = new ExerciseID_Page(driver);
			EID = EI.Exercise_Id();
			
			if(ModuleUI.equals("Learn to read") || ModuleUI.equals("Ratio and Proportion"))
			{
				//Check the dry run table
				if(EID.equals("CLCF1") || EID.equals("CLCF3") || EID.equals("CLCW6"))
				{
					System.out.println("Exercise with dry run : " +EID);
				}
				else
				{
					new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.visibilityOfAllElements(Label));
				}
			}
			else
			{
				new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.visibilityOfAllElements(Description));
			}
			QS.Question_Space();
			

			if(i < cnt-1)
			{
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
				new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.visibilityOfAllElements(Next));
				Next.click();
				Nvar++;
			}
			else
			{
				System.out.println("Visited Exercises By Next : " +Nvar);
			}
		}
	}

	//@Test
	public void VisitExercise_method() throws Exception
	{
//		EI = new ExerciseID_Page(driver);
//		EID = EI.Exercise_Id();
		QS = new QuestionsSpace_Page(driver);
//		QS.CreateFile();
//		QS.InsertData();
//		QS.InsertData();
	}
}

