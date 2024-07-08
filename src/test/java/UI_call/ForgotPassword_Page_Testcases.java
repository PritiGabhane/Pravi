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

public class ForgotPassword_Page_Testcases {
	WebDriver driver;
	Forgot_Password_Page FP;
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
	public void CheckUsername_method() throws Exception
	{
		FP = new Forgot_Password_Page(driver);
		FP.VisitUrl(Config.getUrl());
		//Without username
		FP.Open_ForgotPassword("");
		//With spaces
		FP.Open_ForgotPassword("       ");
	}
	
	@Test(priority = 2)
	public void ForgotPassword_method() throws Exception
	{
		//With valid user
		FP.Open_ForgotPassword(Config.getValidUserID());
		FP.SignIn(Config.getValidUserID());
		FP.CheckEmail(Config.getValidUserID());
		FP.VerifyCaptcha();
		FP.CheckEmail(Config.getValidUserID());
	}
	
	@Test(priority = 3)
	public void WithoutDetails_method() throws Exception
	{
		System.out.println("* Reset Password without details ---> ");
		FP.ResetPassword("", "");
	}
	
	@Test(priority = 4)
	public void WithoutPass1_method() throws Exception
	{
		System.out.println("* Reset Password without Pass1 ---> ");
		FP.ResetPassword("", "12345");
	}
	
	@Test(priority = 5)
	public void WithoutPass2_method() throws Exception
	{
		System.out.println("* Reset Password without Pass2 ---> ");
		FP.ResetPassword("12345", "");
	}
	
	@Test(priority = 6)
	public void Less8_Details_method() throws Exception
	{
		System.out.println("* Reset Password with same but less than 8 characters ---> ");
		FP.ResetPassword("12345", "12345");
	}
	
	@Test(priority = 7)
	public void DifferentDetails_method() throws Exception
	{
		System.out.println("* Reset Password with different and equal/greater than 8 characters ---> ");
		FP.ResetPassword(Config.getNew_Pass(), Config.getInvalid_Pass());
	}
	
	@Test(priority = 8)
	public void CorrectDetails_method() throws Exception
	{
		System.out.println("* Reset Password with correct details ---> ");
		FP.ResetPassword(Config.getNew_Pass(), Config.getNew_Pass());
	}

	@AfterTest
	public void DriverClose()
	{
		driver.quit();
	}

}
