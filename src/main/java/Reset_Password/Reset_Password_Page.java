package Reset_Password;

import java.awt.AWTException;
import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reset_Password_Page {
	
	WebDriver driver; String actualurl, currenturl;
	
	@FindBy(xpath = "//a[@class = 'button primary small' and @href = '/login']") WebElement SignInbtn;
	
	@FindBy(xpath = "//input[@id = 'one' and @name = 'username']") WebElement UserName;
	
	@FindBy(xpath = "//input[@id = 'two' and @name = 'password']") WebElement Password;
	
	@FindBy(xpath = "//button[@class = 'button primary' and text() = 'LOGIN ']") WebElement LoginBtn;
	
	@FindBy(xpath = "//div[@class ='pyui-msg error']") WebElement LoginError;
	
	@FindBy(xpath = "//h3[@class='pyui_banner-title']") WebElement WelcomeUser;
	
	@FindBy(xpath = "//button[@class = 'pyui_avatar' and @title = 'Profile']") WebElement Profilebtn;
	
	@FindBy(xpath = "//a[@class = 'pyui_profile' and @title = 'Reset']") WebElement Reset;
	
	@FindBy(xpath = "//button[@class = 'link-button' and text() = '< Back']") WebElement Backbtn;
	
	@FindBy(xpath = "//input[@id = 'current' and @type = 'password']") WebElement Current;
	
	@FindBy(xpath = "//input[@id = 'new_password' and @type = 'password']") WebElement NewPass;
	
	@FindBy(xpath = "//input[@id = 're_new_password' and @type = 'password']") WebElement ReNewPass;
	
	@FindBy(xpath = "//button[@class = 'button primary' and text() = 'Reset']") WebElement ResetOTPbtn;
	
	@FindBy(xpath = "//div[@class ='pyui-msg error']") WebElement Error;
	
	@FindBy(xpath = "//*[@class ='pyui-msg success']") WebElement Success;
	
	public Reset_Password_Page(WebDriver driver)
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
	public void CurrentUrl(String currenturl) throws Exception
	{
		Thread.sleep(1000);
		actualurl = driver.getCurrentUrl();
		
		//Check the url
		if(!actualurl.equals(currenturl))
		{
			System.out.println("User navigates to the " + actualurl + " Page.");
		}	
		else
		{
			System.out.println("User is on the same(" + currenturl + ") Page.");
		}
	}

	//@Test
	public void CheckError() throws Exception
	{
		Thread.sleep(1000);
		boolean DispalyError = Error.isDisplayed();
		// Check the error is present or not
		if (DispalyError) {
			System.out.println("Error - " + Error.getText());
		}
	}
	
	//@Test
	public void CheckSuccess() throws Exception
	{
		Thread.sleep(700);
		//Check the success is present or not
		if(Success.isDisplayed())
		{
			System.out.println("Success - " + Success.getText());
		}
	}
	
	//@Test
	public void LoginUser(String User, String Pass) throws Exception
	{  
		currenturl = driver.getCurrentUrl();
		UserName.sendKeys(User);
		Password.sendKeys(Pass);
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(LoginBtn)).click();
		CurrentUrl(currenturl);
	}
	
	//@Test
	public void Open_ResetPassword() throws Exception
	{
		currenturl = driver.getCurrentUrl();
		//Open the profile
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(Profilebtn)).click();

		//Open reset password
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(Reset)).click();
		
		CurrentUrl(currenturl);
	}
	
	//@Test
	public void Back() throws Exception
	{
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(Backbtn)).click();
		System.out.println("User clicked the '< Back' button");
		Open_ResetPassword();
	}
	
	//@Test
	public void ResetPassword(String User, String CurrentPass, String Pass1, String Pass2) throws Exception
	{
		Current.clear(); NewPass.clear(); ReNewPass.clear();
		Current.sendKeys(CurrentPass);
		NewPass.sendKeys(Pass1);
		ReNewPass.sendKeys(Pass2);
		currenturl = driver.getCurrentUrl();
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(ResetOTPbtn)).click();
		try 
		{
			CheckError();
		} 
		catch(NoSuchElementException e) 
		{
			CurrentUrl(currenturl);
			CheckSuccess();
			LoginUser(User, Pass1);
		}	
	}
}
