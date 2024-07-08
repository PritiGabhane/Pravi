package Pravi_UI;

import java.awt.AWTException;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
//import org.testng.annotations.Test;

public class Login_Page 
{
	WebDriver driver;
	String actualurl;

	@FindBy(xpath = "//a[@class = 'button primary small' and @href = '/login']") WebElement SignInbtn;

	@FindBy(xpath = "//input[@id = 'one' and @name = 'username']") WebElement UserName;

	@FindBy(xpath = "//input[@id = 'two' and @name = 'password']") WebElement Password;

	@FindBy(xpath = "//button[@class = 'button primary' and text() = 'LOGIN ']") WebElement LoginBtn;

	@FindBy(xpath = "//div[@class ='pyui-msg error']") WebElement LoginError;
	
	@FindBy(xpath = "//h3[@class='pyui_banner-title']") WebElement WelcomeUser;
	
	@FindBy(xpath = "//button[@class = 'pyui_avatar' and @title = 'Profile']") WebElement Profilebtn;
	
	@FindBy(xpath = "//a[@class = 'pyui_profile' and @title = 'Logout']") WebElement SignOutbtn;
	
	public Login_Page(WebDriver driver)
	{
		//Call to webdriver
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void VisitUrl(String Website) throws AWTException {
		// Navigate to web page
		driver.get(Website);
		System.out.println("Welcome to Pravi");

		// Maximizing window
		driver.manage().window().maximize();
		System.out.println("Window is maximized");
		
		//Navigate to login page
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(SignInbtn)).click();
		System.out.println("User is on the login page");
	}
	
	//@Test
	public void LoginUser(String User, String Pass)
	{  
		//Give inputs to login user
		UserName.sendKeys(User);
		Password.sendKeys(Pass);
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(LoginBtn)).click();
	}

	//@Test
	public void TextFields_clear()
	{
		//Clear textfields
		UserName.clear();
		Password.clear();
	}

	//@Test
	public void CurrentUrl()
	{
		try 
		{
			Thread.sleep(1000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		actualurl = driver.getCurrentUrl();
	}

	//@Test
	public void Verify_User(String LoginUrl)
	{
		//Verify the Valid user
		if(actualurl.equals(LoginUrl))
		{
			new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.visibilityOf(LoginError));
			System.out.println("Given information is invalid - " + LoginError.getText());
		}
		else
		{
			new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.invisibilityOf(LoginBtn));
			new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.visibilityOf(WelcomeUser));
			System.out.println("User Logged In Successfully - " + WelcomeUser.getText());
		}
	}
	
	//@Test
	public void SignOut()
	{
		//Open the profile
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(Profilebtn)).click();

		//Open reset password
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(SignOutbtn)).click();
		CurrentUrl();
		System.out.println("User is on the " + actualurl + " page.");
	}
}
