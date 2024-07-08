package UI_call;

import java.awt.AWTException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import Pravi_UI.*;
import dataProvider.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Home_Page_Testcase 
{
	WebDriver driver; int count; Home_Page HP; UIReader Config;

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
	public void URL_method() throws Exception
	{
		HP = new Home_Page(driver);
		HP.VisitUrl(Config.getUrl());
		HP.SecureUrl();
		HP.Verify_Title(Config.getTitle());
	}
	
	@Test(priority = 2)
	public void Tabs_method() throws Exception
	{
		HP.Tabs_Action();
	}
	
	@Test(priority = 3)
	public void Skills_method() throws Exception
	{
		HP.Check_Skills();
	}
	
	@Test(priority = 4)
	public void FAQS_method() throws Exception
	{
		HP.Check_FAQS();
	}
	
	@Test(priority = 5)
	public void Contact_method() throws Exception
	{
		HP.Contact_US(Config.getName(), Config.getEmail(), Config.getMobile(), Config.getMessage());
		HP.Verify_Email();
	}
	
	@Test(priority = 6)
	public void Menu_method() throws Exception
	{
		HP.Menu_Action(Config.getUrl());
	}
	
	@Test(priority = 7)
	public void SignIn_method() throws Exception
	{
	HP.Verify_SignIn(Config.getLoginUrl());
	}
	
	@AfterTest
	public void DriverClose()
	{
		driver.quit();
	}

}