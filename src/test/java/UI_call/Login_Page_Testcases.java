package UI_call;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pravi_UI.Login_Page;
import dataProvider.UIReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_Page_Testcases {

	WebDriver driver; Login_Page LP; UIReader Config;

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
	public void Empty_method() throws AWTException {
		LP = new Login_Page(driver);
		LP.VisitUrl(Config.getUrl());
		
		System.out.println("* Give the input fields empty ---> ");
		LP.LoginUser("", "");
		LP.CurrentUrl();
		LP.Verify_User(Config.getLoginUrl());
	}

	@Test(priority = 2)
	public void Username_method() {
		System.out.println("* Give the input field username ---> ");
		LP.TextFields_clear();
		LP.LoginUser(Config.getValidUserID(), "");
		LP.CurrentUrl();
		LP.Verify_User(Config.getLoginUrl());
	}
	
	@Test(priority = 3)
	public void Password_method() {
		System.out.println("* Give the input field password ---> ");
		LP.TextFields_clear();
		LP.LoginUser("", Config.getInvalid_Pass());
		LP.CurrentUrl();
		LP.Verify_User(Config.getLoginUrl());
	}
	
	@Test(priority = 4)
	public void InvalidUsername_method() {
		System.out.println("* Give the invalid username and valid password ---> ");
		LP.TextFields_clear();
		LP.LoginUser(Config.getInvalidUserID(), Config.getValid_Pass());
		LP.CurrentUrl();
		LP.Verify_User(Config.getLoginUrl());
	}
	
	@Test(priority = 5)
	public void InvalidPassword_method() {
		System.out.println("* Give the valid username and invalid password ---> ");
		LP.TextFields_clear();
		LP.LoginUser(Config.getValidUserID(), Config.getInvalid_Pass());
		LP.CurrentUrl();
		LP.Verify_User(Config.getLoginUrl());
	}
	
	@Test(priority = 6)
	public void InvalidUser_method() {
		System.out.println("* Give the invalid username and password ---> ");
		LP.TextFields_clear();
		LP.LoginUser(Config.getInvalidUserID(), Config.getInvalid_Pass());
		LP.CurrentUrl();
		LP.Verify_User(Config.getLoginUrl());
	}
	
	@Test(priority = 7)
	public void ValidUser_method() {
		System.out.println("* Give the valid username and password ---> ");
		LP.TextFields_clear();
		LP.LoginUser(Config.getValidUserID(), Config.getValid_Pass());
		LP.CurrentUrl();
		LP.Verify_User(Config.getLoginUrl());
	}
	
	@Test(priority = 8)
	public void Signout_method()
	{
		LP.SignOut();
	}
	
	@AfterTest
	public void DriverClose()
	{
		driver.quit();
	}
}

