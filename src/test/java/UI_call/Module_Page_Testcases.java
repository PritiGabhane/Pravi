package UI_call;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pravi_UI.*;
import dataProvider.UIReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Module_Page_Testcases {
	WebDriver driver; Login_Page LP; Modules_Page MP; UIReader Config;

	@BeforeTest
	public void Driver_method() throws AWTException 
	{
		Config = new UIReader();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
	}

	@Test(priority = 1)
	public void Login_method() throws AWTException {
		LP = new Login_Page(driver);
		LP.VisitUrl(Config.getUrl());
		LP.LoginUser(Config.getValidUserID(), Config.getValid_Pass());
		LP.CurrentUrl();
		LP.Verify_User(Config.getLoginUrl());
	}
	
	@Test(priority = 2)
	public void Track_method()
	{
		MP = new Modules_Page(driver);
		MP.TracksClick();
		MP.SelectTrack(Config.getTrack());
	}
	
	@Test(priority = 3)
	public void Module_method()
	{
		MP.SelectType();
		MP.SelectModule(Config.getModule());
	}
	
	@Test(priority = 4)
	public void UserAction_method() throws Exception
	{
		MP.UserAction(Config.getUserAction());
		MP.CountExercises();
	}
	
	@Test(priority = 5)
	public void OpenExercise_method()
	{
		MP.SelectPage(Config.getPage());
		MP.OpenExercise(Config.getExercise());
	}

	@AfterTest
	public void DriverClose()
	{
		driver.quit();
	}
}
