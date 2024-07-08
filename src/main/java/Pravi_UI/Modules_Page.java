package Pravi_UI;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
//import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Modules_Page {
	WebDriver driver; int cnt = 0, PageNo = 0, ExerciseNo = 0; String Category;

	@FindBy(xpath = "//*[@href='/tracks']") WebElement Tracks;

	@FindBy(xpath = "//div[@class='pyui_tracks-container']//button") List<WebElement> Track_list;
	
	@FindBy(xpath = "//span[@class='track-title']") List<WebElement> Track_name;

	@FindBy(xpath = "//*[@href='/tracks/Modules']") WebElement Module;

	@FindBy(xpath = "//section[@class='pyui_main-container-section-content']//ul//li") List<WebElement> Module_list;
	
	@FindBy(xpath = "//h4[@class = 'abc']") WebElement ModuleName;
	
	@FindBy(xpath = "//nav[@class='pyui_exercises-status-tabs']//ul//li") List<WebElement> UserAction_list;

	@FindBy(xpath = "//a[@class = 'nextbtn']") WebElement nextbtn;
	
	@FindBy(xpath = "//input[@placeholder = 'Search by title' and @type = 'search']") WebElement Search;
	
	public Modules_Page(WebDriver driver)
	{
		//Call to webdriver
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//@Test
	@SuppressWarnings("deprecation")
	public void TracksClick()
	{
		//Click Tracks Tab
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		Tracks.click();
	}

	//@Test
	public void SelectTrack(String Track)
	{
		//Select the track
		System.out.println("Tracks values are : " + Track_list.size());			
		for(int i=0; i<Track_list.size(); i++)
		{                  
			if(Track_name.get(i).getText().contains(Track))
			{
				System.out.println("Selected Track : " +Track_name.get(i).getText());
				Track_list.get(i).click();
			}
		}
	}

	//@Test
	public void SelectType()
	{
		//Click Module Tab
		Module.click();
		System.out.println("User selected Modules");
	}

	//@Test
	public void SelectModule(String Module)
	{
		//Select the module
		System.out.println("Modules values are : " + Module_list.size());
		for(int i=0; i<Module_list.size(); i++)
		{        
			if(Module_list.get(i).getText().contains(Module))
			{
				System.out.println("Selected Module : " +Module_list.get(i).getText());
				Module_list.get(i).click();
			}
		}
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
	public void SelectPage(String Page)
	{
		PageNo = Integer.parseInt(Page);
	
		//Open the manual page
		for(int i = 1; i < PageNo; i++)
		{
			nextbtn.click();
		}
		System.out.println("Given page is opened " + PageNo);
	}

	//@Test
	public void OpenExercise(String Exercise)
	{
		ExerciseNo = Integer.parseInt(Exercise);
		
		//Open the given exercise
		WebElement Exercisebtn = driver.findElement(By.xpath("(//a[@href = '/tracks/Modules/Exercises/Editor'])["+ ExerciseNo +"]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();", Exercisebtn);

		String ExerciseName = driver.findElement(By.xpath("(//div[@class= 'pyui_track_list-item-content-title'])["+ ExerciseNo +"]")).getText();
		new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(Exercisebtn)).click();		
		System.out.println("Given exercise is opened " + ExerciseName);
	}
	
	//@Test
	public void SearchCategory(String Category)
	{
		Search.sendKeys(Category + Keys.ENTER);
		
	}
}
