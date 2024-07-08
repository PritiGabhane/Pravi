package Functioncall;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import Pravi.*;
import Visit_Exercises.VisitExercise_Page;
import dataProvider.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Visit_Exercises_Testcase 
{
	WebDriver driver;
	int count;
	Launch_Login LP; VisitExercise_Page VP;
	FunctionalReader Config;

	@BeforeTest
	public void Driver_method() throws AWTException
	{
		Config = new FunctionalReader();
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
	public void VisitExercise_Page_Method() throws Exception 
	{
		VP = new VisitExercise_Page(driver);
		VP.UserAction(Config.getUserAction());
		count = VP.CountExercises();
		if(count > 0)
		{
			VP.OpenExercise(Config.getPage(), Config.getExercise());
			VP.VisitNext(Config.getModule());
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