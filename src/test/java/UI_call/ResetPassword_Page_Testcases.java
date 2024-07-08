package UI_call;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Reset_Password.*;
import dataProvider.UIReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ResetPassword_Page_Testcases {
	WebDriver driver;
	Reset_Password_Page RP;
	UIReader Config;

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
	public void Open_Reset_method() throws Exception
	{
		RP = new Reset_Password_Page(driver);
		RP.VisitUrl(Config.getUrl());
		RP.LoginUser(Config.getValidUserID(), Config.getValid_Pass());
		RP.Open_ResetPassword();
		RP.Back();
	}
	
	@Test(priority = 2)
	public void WithoutDetails_method() throws Exception
	{
		System.out.println("* Reset Password without details ---> ");
		RP.ResetPassword(Config.getValidUserID(), "", "", "");
	}
	
	@Test(priority = 3)
	public void WithoutPass1_method() throws Exception
	{
		System.out.println("* Reset Password without Pass1 ---> ");
		RP.ResetPassword(Config.getValidUserID(), Config.getValid_Pass(), "", "12345");
	}
	
	@Test(priority = 4)
	public void WithoutPass2_method() throws Exception
	{
		System.out.println("* Reset Password without Pass2 ---> ");
		RP.ResetPassword(Config.getValidUserID(), Config.getValid_Pass(), "12345", "");
	}
	
	@Test(priority = 5)
	public void Less8_Details_method() throws Exception
	{
		System.out.println("* Reset Password with same but less than 8 characters ---> ");
		RP.ResetPassword(Config.getValidUserID(), Config.getValid_Pass(), "12345", "12345");
	}
	
	@Test(priority = 6)
	public void DifferentDetails_method() throws Exception
	{
		System.out.println("* Reset Password with different and equal/greater than 8 characters ---> ");
		RP.ResetPassword(Config.getValidUserID(), Config.getValid_Pass(), Config.getNew_Pass(), Config.getInvalid_Pass());
	}
	
	@Test(priority = 7)
	public void WithoutCurrent_method() throws Exception
	{
		System.out.println("* Reset Password without current password ---> ");
		RP.ResetPassword(Config.getValidUserID(), "", Config.getNew_Pass(), Config.getNew_Pass());
	}
	
	@Test(priority = 8)
	public void IncorrectCurrent_method() throws Exception
	{
		System.out.println("* Reset Password with incorrect current password ---> ");
		RP.ResetPassword(Config.getValidUserID(), Config.getInvalid_Pass(), Config.getNew_Pass(), Config.getNew_Pass());
	}
	
	@Test(priority = 9)
	public void CorrectDetails_method() throws Exception
	{
		System.out.println("* Reset Password with correct details ---> ");
		RP.ResetPassword(Config.getValidUserID(), Config.getValid_Pass(), Config.getNew_Pass(), Config.getNew_Pass());
	}
	
	@AfterTest
	public void DriverClose()
	{
		driver.quit();
	}

}
