package Functioncall;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import Pravi.*;
import Quiz.*;
import dataProvider.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SolveQuiz_Testcase {
	WebDriver driver; 
	Launch_Login LP; StartQuiz_Page SP; SolveQuiz_Page SQP; FunctionalReader Config; int count;

	@BeforeTest
	public void Driver_method() throws AWTException
	{
		Config= new FunctionalReader();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
	}

	@Test(priority = 1)
	public void InvalidUser_method() throws AWTException 
	{
		LP = new Launch_Login(driver);
		LP.Launch_App(Config.getUrl());
		LP.Login_APP(Config.getLoginUrl(), Config.getUserID(), Config.getPass());
		LP.Verify_Login(Config.getLoginUrl());
	}

	@Test(priority = 2)
	public void Tracks_method()
	{
		LP.SelectTrack(Config.getTrack());
	}

	@Test(priority = 3)
	public void StartQuiz_Page_Method() throws Exception {
		SP = new StartQuiz_Page(driver);
		SP.ClickEvaluation();
		SP.QuizType(Config.getQuizType());
		count = SP.SelectQuiz(Config.getQuizName());
	}

	@Test(priority = 4)
	public void SolveQuiz_Page_Method() throws Exception {
		if (count == 0) {
			System.out.println("Quiz is not available");
		}
		else 
		{
			SQP = new SolveQuiz_Page(driver);
			SQP.CheckQuiz(Config.getQuizType());
			SQP.StartQuiz();
			SQP.Solve_Quiz();
		}		
	}

	@AfterTest
	public void DriverClose() {
		driver.quit();
	}
}