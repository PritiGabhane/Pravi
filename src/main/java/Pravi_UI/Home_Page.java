package Pravi_UI;

import java.awt.AWTException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home_Page {
	WebDriver driver;
	String actualurl, title; int titleLength;
	String Tabs[] = {"#homepage", "#courses", "#whyus", "#aboutus", "#faqs", "#contactus", "/terms", "/privacy"};
	
	@FindBy(xpath = "//label[@class = 'mobileMenu']") WebElement Menu;

	@FindBy(xpath = "//header[@class = 'pyui_main-appbar']/ul/li") List<WebElement> Navlinks;
	
	@FindBy(xpath = "//div[@class = 'display-text-group']//div[@class = 'read-more-button']") List<WebElement> ReadMore;
	
	@FindBy(xpath = "//a[@class = 'tabsp' and @href = '#courses']") WebElement Skills;
	
	@FindBy(xpath = "//div[@class = 'slide-content']/section/header") WebElement Header;
	
	@FindBy(xpath = "//div[@class = 'slide-content']/section/section/div") List<WebElement> Courses;
	
	@FindBy(xpath = "//*[@id=\"courses\"]/section/section[1]/div/button[2]") WebElement Nextbtn;
	
	@FindBy(xpath = "//a[@class = 'tabsp' and @href = '#faqs']") WebElement FAQ;
	
	@FindBy(xpath = "//div[@class = 'Collapsible']/span") List<WebElement> FAQS;
	
	@FindBy(xpath = "//a[@class = 'tabsp' and @href = '#contactus']") WebElement Contactus;		
	
	@FindBy(xpath = "//div[@class = 'inline-form']/button[@class = 'button primary' and text() = 'Submit']") WebElement Submitbtn;
	
	@FindBy(xpath = "//a[@href = 'mailto:support@pravi.co' and text() = ' support@pravi.co ']") WebElement Mail;
	
	@FindBy(xpath = "//ul[@class = 'footer-container-menu']/li") List<WebElement> Menulinks;
	
	@FindBy(xpath = "//a[@class = 'button primary small' and @href = '/login']") WebElement SignInbtn;
	
	public Home_Page(WebDriver driver) {
		// Call to webdriver
		this.driver = driver;
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
	}

	public void CurrentUrl() {
		// Get the current page url
		actualurl = driver.getCurrentUrl();
	}

	 //@Test
	public void SecureUrl() {
		// Verifying URL security
		CurrentUrl();
		if (actualurl.contains("https:")) {
			System.out.println("The Website is secure");
		} else {
			System.out.println("The Website is not secure");
		}
	}

	 //@Test
	public void Verify_Title(String GetTitle) {
		// Retrieving web page title
		title = driver.getTitle();
		titleLength = driver.getTitle().length();
		if (title.equals(GetTitle)) {
			System.out.println("The page title is : " + title);
			System.out.println("Length of the title is : " + titleLength);
		} else {
			System.out.println("Page title is not matched");
		}
	}

	 //@Test
	public void Tabs_Action() throws InterruptedException 
	{
		//Check the menu is present or not
		if(Menu.isDisplayed())
		{
			Menu.click();
		}
		 
		System.out.println("Navlinks values are : " + Navlinks.size());
		for(int i = 0; i < Navlinks.size() - 1; i++) 
		{
			//Get the tabname and click the tab
			String Tab = Navlinks.get(i).getText();
//			System.out.println("Selceted value = " + Tab + "  " + Tabs[i]);
			WebElement Navlink = driver.findElement(By.xpath("//header[@class = 'pyui_main-appbar']/ul/li/a[@href = '"+ Tabs[i] +"' and text() = '"+ Tab +"']"));
			new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.visibilityOf(Navlink)).click();

			//Check the redirection
			CurrentUrl();
			if(actualurl.contains(Tabs[i]))
			{
				System.out.println("User redirects the " + Tab + " - " + Tabs[i] + " Tab page.");
			}
			else
			{
				System.out.println(Tab + " - " + Tabs[i] + " User is on the same page");
			}
		}
	}
	
	//@Test
	public void Check_Skills() throws InterruptedException
	{
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(Skills)).click();
		
		//Check the skills
		int count = 0, size = ReadMore.size(), btn = 0;
		for(int j = 0; j < size; j++)
		{
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", ReadMore.get(btn));
			count++;
		}
		System.out.println(count + " ways are present to Master your computer programming.");
		System.out.println("Read more button clicked " + count + " times out of " + size);
		
		int csize = Courses.size(), ccount = 0;
		for(int k = 0; k <= csize; k++)
		{
			//Get header name
			Thread.sleep(1000);
			Header = driver.findElement(By.xpath("//div[@class = 'slide-content']/section/header"));
			System.out.println("------* " + Header.getText() + " *------");
			
			//Get Courses names
			for(int l = 0; l < Courses.size(); l++)
			{
				Courses = driver.findElements(By.xpath("//div[@class = 'slide-content']/section/section/div"));
				System.out.println(l+1 + ")" + Courses.get(l).getText());
			}
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", Nextbtn);
			ccount++;
		}
		System.out.println(ccount + " Courses available to learn your skills.");
	}

	//@Test
	public void Check_FAQS() throws InterruptedException
	{
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(FAQ)).click();
		System.out.println("FAQs page opened.");

		//Check the FAQS
		int count = 0;
		for(int k = 0; k < FAQS.size(); k++)
		{
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", FAQS.get(k));
			count++;
		}
		System.out.println(FAQS.size() + " Out of " + count + " FAQs are present and Opened.");
	}
	
	//@Test
	public void Contact_US(String Name, String Email, String Mobile, String Message) throws Exception
	{
		 new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(Contactus)).click();
		 
		//Get details and Contact to pravi
		String Contact[] = {Name, Email, Mobile, Message};
		for(int l = 0, m = 1; l < Contact.length; l++, m++)
		{
			WebElement ContactUS = driver.findElement(By.xpath("(//div[@class = 'inline-form']/div/*[@class = 'input'])["+ m +"]"));
			ContactUS.sendKeys(Contact[l]);
			ContactUS.sendKeys(Keys.TAB);
		}
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", Submitbtn);
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.visibilityOf(Submitbtn));
	}
	
	//@Test
	public void Verify_Email()
	{
		//Check email is clickable
		if(Mail.isDisplayed() && Mail.isEnabled())
		{
//			new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.visibilityOf(Mail)).click();;
			System.out.println("Mail is clickable");
		}
	}
	
	//@Test
	public void Menu_Action(String website) throws InterruptedException
	{
		System.out.println("Menulinks values are : " + Menulinks.size());
		for(int i = 0 ; i < Menulinks.size(); i++) 
		{
			//Get the menuname and decide the webelement
			String Menu = Menulinks.get(i).getText();
//			System.out.println("Selceted value = " + i + " " + Menu + "  " + Tabs[i]);
			WebElement Menulink = driver.findElement(By.xpath("//div[@class = 'footer-container-col2']/ul/li/a[@href = '"+ Tabs[i] +"' and text() = '"+ Menu +"']"));
			
			//Scroll the page and click the webelement
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", Menulink);
			Thread.sleep(1000);
			new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(Menulink)).click();
			
			//Check the redirection
			Thread.sleep(1000);
			CurrentUrl();
			if(actualurl.contains(Tabs[i]))
			{
				System.out.println("User redirects the " + Menu + " - " + Tabs[i] + " Menu page.");
			}
			else
			{
				System.out.println(Menu + " - " + Tabs[i] + " = User is on the same page");
			}
		}
	}
		
	//@Test
	public void Verify_SignIn(String SignInUrl)
	{
		new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.elementToBeClickable(SignInbtn)).click();
		
		//Verify sign up button functionality
		CurrentUrl();
		if(actualurl.equals(SignInUrl))
		{
			System.out.println("User navigate to login page");
		}
		else
		{
			System.out.println("SingIn button is not in working");
		}
	}
}
	
