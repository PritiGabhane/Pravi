package Check_Answers;

import java.time.Duration;
import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import Pravi.*;

public class CheckAnswers_Page {
	int Nvar = 1, Pvar = 1, cnt = 0;
	String sizeAvailable, Category;
	WebDriver driver;
	ExerciseAnswers_Page EA;
	
	@FindBy(xpath = "//*[@href='/tracks/Modules']") WebElement Module;
	
	@FindBy(xpath = "//nav[@class='pyui_exercises-status-tabs']//ul//li") List<WebElement> UserAction_list;

	@FindBy(xpath = "//a[text() = '1']") WebElement First;

	@FindBy(xpath = "(//a[@href = '/tracks/Modules/Exercises/Editor'])[1]") WebElement Exercise1;
	
	@FindBy(xpath = "(//a[@href = '/tracks/Modules/Exercises/Editor'])[2]") WebElement Exercise2;

	@FindBy(xpath = "//span[text()='Next']") WebElement Next;

	@FindBy(xpath = "//span[text()='Previous']") WebElement Previous;

	@FindBy(xpath = "//label[@class = 'form-label']") WebElement Label;
	
	@FindBy(xpath = "//div[@class= 'problem-description']") WebElement Description;
	
	@FindBy(xpath = "//button[text()='Check Answers']") WebElement CheckAnswersBtn;

	@FindBy(xpath = "//button[text()='Mark Complete']") WebElement MarkCompleteBtn;

	public CheckAnswers_Page(WebDriver driver)
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
		
//		Python exercises - "Pandas series - 15" || "Accessing column - 6"
		if(Exercise1.getText().contains("Pandas series - 15")) 
		{
			Exercise2.click();
		}
		else
		{
			Exercise1.click();
		}
	}

	//@Test
	public void VisitNext(String ModuleUI) throws Exception
	{   
		//Check answers for all exercises
		for(int i = 0; i < cnt; i++)
		{

			if(ModuleUI.equals("Learn to read"))
			{
				new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOfAllElements(Label));
			}
			else
			{
				new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOfAllElements(Description));
			}

			VisitExercise_method();
			//Checkanswers and Markcomplete the exercise
			new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(CheckAnswersBtn)).click();
			new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(MarkCompleteBtn)).click();
			if(i < cnt - 1)
			{
//				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();", Next);
				new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(Next)).click();
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
		EA = new ExerciseAnswers_Page(driver);
		EA.API_Answers();
		EA.Answers_UI();
	}
}

