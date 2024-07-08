package Pravi;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Launch_Login {
	WebDriver driver; String actualurl;
	
	@FindBy(xpath = "//a[@href='/login' and text() = 'SIGN IN']") WebElement SignInBtn;

	@FindBy(xpath = "//*[@name = 'username']") WebElement UserName;

	@FindBy(xpath = "//*[@name = 'password']") WebElement Password;

	@FindBy(xpath = "//button[text()='LOGIN ']") WebElement LoginBtn;

	@FindBy(xpath = "//div[@class='pyui-msg error']") WebElement LoginError;
	
	@FindBy(xpath = "//h3[@class='pyui_banner-title']") WebElement WelcomeUser;
	
	@FindBy(xpath = "//*[@href='/tracks']") WebElement Tracks;

	@FindBy(xpath = "//div[@class='pyui_tracks-container']//button") List<WebElement> Track_list;

	@FindBy(xpath = "//span[@class='track-title']") List<WebElement> Track_name;

	@FindBy(xpath = "//*[@href='/tracks/Modules']") WebElement Module;

	@FindBy(xpath = "//section[@class='pyui_main-container-section-content']//ul//li") List<WebElement> Module_list;

	public Launch_Login(WebDriver driver)
	{
		//Call to webdriver
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

    //@Test
	public void Launch_App(String Website) throws AWTException
	{
		//Navigate to web page
		driver.get(Website);
		System.out.println("Welcome to Pravinyam");
		
		//Maximize window
		driver.manage().window().maximize();
		
		//Set zoom level at 75%
		 for(int i=1; i<4; i++)
		 {
			 Robot robot = new Robot();
			 robot.keyPress(KeyEvent.VK_CONTROL);	robot.keyPress(KeyEvent.VK_MINUS);
			 robot.keyRelease(KeyEvent.VK_CONTROL);	robot.keyRelease(KeyEvent.VK_MINUS);
			 
//			 driver.getWindowHandle().
		}
	}

	//@Test
	public void Login_APP(String SignInUrl, String User, String Pass)
	{  
		//Click sign up button
		SignInBtn.click();
		
		//Get the current page url
		actualurl = driver.getCurrentUrl();
		if(actualurl.equals(SignInUrl))
		{
			System.out.println("User navigate to login page");
		}
		else
		{
			System.out.println("SingIn button is not in working");
		}
		
		//Give inputs to login user
		UserName.sendKeys(User);
		Password.sendKeys(Pass);
		LoginBtn.click();
	}

	//@Test
	public void Verify_Login(String LoginUrl)
	{
		//Get the current page url
		actualurl = driver.getCurrentUrl();
				
		//Verify the Valid user
		if(actualurl.equals(LoginUrl))
		{
			new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.invisibilityOf(LoginBtn));
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(WelcomeUser));
			System.out.println("User Logged In Successfully - " + WelcomeUser.getText());
		}
		else
		{
			System.out.println("User Not Logged IN Successfully");
		}
	}
	
	//@Test
	@SuppressWarnings("deprecation")
	public void SelectTrack(String Track) {
		// Click Tracks Tab
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		Tracks.click();
		
		// Select the track
		System.out.println("Tracks values are : " + Track_list.size());
		for (int i = 0; i < Track_list.size(); i++) {
			if (Track_name.get(i).getText().contains(Track)) {
				System.out.println("Selected Track : " + Track_name.get(i).getText());
				Track_list.get(i).click();
			}
		}
	}

	//@Test
	public void SelectModule(String module_name) {
		// Click Module Tab
		Module.click();
		System.out.println("User selected Modules");
				
		// Select the module
		System.out.println("Modules values are : " + Module_list.size());
		for (int i = 0; i < Module_list.size(); i++) {
			if (Module_list.get(i).getText().contains(module_name)) {
				System.out.println("Selected Module : " + Module_list.get(i).getText());
				Module_list.get(i).click();
			}
		}
	}
}
