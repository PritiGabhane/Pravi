package Reset_Password;

import java.awt.AWTException;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Forgot_Password_Page {
	WebDriver driver; String actualurl, currenturl; Scanner myObj = new Scanner(System.in);

	@FindBy(xpath = "//a[@class = 'button primary small' and @href = '/login']") WebElement Loginbtn;
	
	@FindBy(xpath = "//button[@class='link-button small' and text() = 'Forgot password']") WebElement Forgotbtn;
	
	@FindBy(xpath = "//button[@class = 'link-button' and text() = '< Sign in']") WebElement SignInbtn;
	
	@FindBy(xpath = "//input[@id = 'one' and @name = 'username']") WebElement UserName;
	
	@FindBy(xpath = "//div[@class ='pyui-msg error']") WebElement Error;
	
	@FindBy(xpath = "//*[@class ='pyui-msg success']") WebElement Success;
	
	@FindBy(xpath = "//input[@placeholder = 'Enter captcha']") WebElement InputCaptcha;
	
	@FindBy(xpath = "//button[@class= 'link-button small' and text() = 'Verify and send OTP']") WebElement OTPbtn;
	
	@FindBy(xpath = "//input[@id= 're_new_password' and @type = 'text']") WebElement InputOTP;
	
	@FindBy(xpath = "//button[@class = 'button primary' and text() = 'Verify OTP']") WebElement VerifyOTPbtn;
	
	@FindBy(xpath = "//input[@id = 'new_password' and @type = 'password']") WebElement NewPass;
	
	@FindBy(xpath = "//input[@id = 're_new_password' and @type = 'password']") WebElement ReNewPass;
	
	@FindBy(xpath = "//button[@class = 'button primary' and text() = 'Reset']") WebElement ResetOTPbtn;
	
	public Forgot_Password_Page(WebDriver driver)
	{
		//Call to webdriver
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//@Test
	public void VisitUrl(String Website) throws AWTException {
		// Navigate to web page
		driver.get(Website);
		System.out.println("Welcome to Pravi");

		// Maximizing window
		driver.manage().window().maximize();
		System.out.println("Window is maximized");
		
		//Navigate to login page
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(Loginbtn)).click();
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
	public void Open_ForgotPassword(String User) throws Exception
	{
		//Give input username to forgot password
		UserName.clear();
		UserName.sendKeys(User);
		currenturl = driver.getCurrentUrl();
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(Forgotbtn)).click();
		Thread.sleep(1000);
		try 
		{
			CheckError();
		} 
		catch(NoSuchElementException e) 
		{
			CurrentUrl(currenturl);
		}
	}
	
	//@Test
	public void SignIn(String User) throws Exception
	{
		//Navigate to login page
		currenturl = driver.getCurrentUrl();
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(SignInbtn)).click();
		CurrentUrl(currenturl);

		UserName.sendKeys(User);

		//Navigate to forgot password page
		currenturl = driver.getCurrentUrl();
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(Forgotbtn)).click();
		CurrentUrl(currenturl);
	}
	
	//@Test
	public void CheckEmail(String user)
	{
		WebElement User = driver.findElement(By.xpath("//input[@id = 'current' and @value = '" + user + "']"));
		boolean DisplayUser = User.isDisplayed(), EnableUser = User.isEnabled();
		if(DisplayUser && EnableUser)
		{
			System.out.println("User is present but not disabled.");
		}
		else
		{
			System.out.println("User is present and disabled.");
		}
	}
	
	//@Test
	public void VerifyCaptcha() throws Exception
	{
		InputCaptcha.clear();
		System.out.print("Enter the Catcha : ");  // Output user input
		InputCaptcha.sendKeys(myObj.nextLine());  // Read user input
		OTPbtn.click();
		try 
		{
			CheckError();
			VerifyCaptcha();
		} 
		catch(NoSuchElementException e) 
		{
			CheckSuccess();
			Give_VerifyOTP();
		}
	}
	
	//@Test
	public void Give_VerifyOTP() throws Exception
	{
		InputOTP.clear();
		System.out.print("Enter the OTP : ");
		InputOTP.sendKeys(myObj.nextLine());
		currenturl = driver.getCurrentUrl();

		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(VerifyOTPbtn)).click();;
		try 
		{
			CurrentUrl(currenturl);
		} 
		catch(NoSuchElementException e) 
		{
			CheckError();
			Give_VerifyOTP();
		}
	}
	
	//@Test
	public void ResetPassword(String Pass1, String Pass2) throws Exception
	{
		NewPass.clear(); ReNewPass.clear();
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
		}
	}
}
