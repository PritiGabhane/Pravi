package Visit_Exercises;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
//import org.testng.annotations.Test;

import Pravi.ExerciseID_Page;

public class VisitExercise_Page {
	int Nvar = 1, Pvar = 1, cnt = 0, PageNo = 0, ExerciseNo = 0, count = 0;
	String sizeAvailable, Category, ModuleUI, EID;
	WebDriver driver; ExerciseID_Page EI;
	
	@FindBy(xpath = "//*[@href='/tracks/Modules']") WebElement Module;

	@FindBy(xpath = "//h4[@class = 'abc']") WebElement ModuleName;
	
	@FindBy(xpath = "//nav[@class='pyui_exercises-status-tabs']//ul//li") List<WebElement> UserAction_list;

	@FindBy(xpath = "//a[@class = 'nextbtn']") WebElement nextbtn;
	
	@FindBy(xpath = "//a[text() = '1']") WebElement First;

	@FindBy(xpath = "//span[text()='Next']") WebElement Next;

	@FindBy(xpath = "//span[text()='Previous']") WebElement Previous;

	@FindBy(xpath = "//label[@class = 'form-label']") WebElement Label;
	
	@FindBy(xpath = "//div[@class= 'problem-description']") WebElement Description;
	
	@FindBy(xpath = "//div[@class = 'editor-debug']") WebElement Editor;

	public VisitExercise_Page(WebDriver driver)
	{
		//Call the driver
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
			//Selected action and click it
			Category = UserAction_list.get(i).getText();
			System.out.println("Selected Action : " + Category);
			UserAction_list.get(i).click();
			}
		}
	}

	//@Test
	public int CountExercises()
	{	
		//Count the exercises in useraction
		cnt = Integer.parseInt(Category.replaceAll("[^0-9]", ""));		
		System.out.println("Total of available exercises : " + cnt);
		return cnt;
	}

	//@Test
	public void OpenExercise(String Page, String Exercise)
	{
		PageNo = Integer.parseInt(Page);
		ExerciseNo = Integer.parseInt(Exercise);
		
		//Open the first exercise
		First.click();
		
//		Open the manual exercise
		for(int i = 1; i < PageNo; i++)
		{
			nextbtn.click();
		}
		
		WebElement Exercisebtn = driver.findElement(By.xpath("(//a[@href = '/tracks/Modules/Exercises/Editor'])["+ ExerciseNo +"]"));
		Exercisebtn.click();
	}

	//@Test
	public void VisitNext(String ModuleUI) throws Exception
	{   
		count = cnt - ((PageNo - 1) * 20 + (ExerciseNo - 1));
		System.out.println("Total exercises to visit : " + count);
		
		//Visit the all exercises
		for(int i = 0; i < count; i++)
		{
			EI = new ExerciseID_Page(driver);
			EID = EI.Exercise_Id();
			System.out.println("Visited Exercises : " +EID);
			
			new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(Editor));
			if(ModuleUI.equals("Learn to read") || ModuleUI.equals("Ratio and Proportion") || ModuleUI.equals("Permutations and Combinations") || ModuleUI.equals("Propositional Logic") || ModuleUI.equals("Sets and Venn Diagrams") || ModuleUI.equals("Percent and Percentile"))
			{
				boolean DLabel = Label.isDisplayed();
				if(DLabel)
				{
					new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(Label));
				}
				else
				{
					System.out.println("Error : " + EID + "Questions are not present....");
				}
				
			}
			else if(ModuleUI.equals("Can you fix it?") || ModuleUI.equals("Want to solve it?")) 
			{
				boolean DDescription = Description.isDisplayed();
				if(DDescription)
				{
					new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOfAllElements(Description));
				}			
				else
				{
					System.out.println("Error : " + EID + "Questions are not present....");
				}
			}
			
			if(i < count - 1)
			{
				Thread.sleep(500);
				Next.click();
				Nvar++;
			}
			else
			{
				System.out.println("Visited Exercises By Next : " + Nvar);
			}
		}

	}

}
