package Functioncall;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import Content.*;
import Pravi.*;
import dataProvider.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class QuestionsSpace_Testcase 
{
	WebDriver driver;
	int count;
	Launch_Login LP; CheckSpaces_Page CP;
	FunctionalReader Config;

	@BeforeTest
	public void Driver_method() throws AWTException
	{
		Config= new FunctionalReader();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
	}

	@Test(priority = 1)
	public void InvalidUser_method() throws AWTException 
	{
		LP = new Launch_Login(driver);
		LP.Launch_App(Config.getUrl());
		LP.Login_APP(Config.getLoginUrl(), Config.getUserID(), Config.getPass());
		LP.Verify_Login(Config.getLoginUrl());
	}

	@Test(priority = 2)
	public void Modules_method()
	{
		LP.SelectTrack(Config.getTrack());
		LP.SelectModule(Config.getModule());
	}
	
	@Test(priority = 3)
	public void CheckSpaces_Page_Method() throws Exception 
	{
		CP = new CheckSpaces_Page(driver);
		CP.UserAction(Config.getUserAction());
		count = CP.CountExercises();
		if(count > 0)
		{
			CP.FirstExercise();
			CP.VisitNext(Config.getModule());
		}
		else
		{
			System.out.println("Exercises are not available");
		}
	}

	@AfterTest
	public void DriverClose()
	{
		driver.quit();
	}
}