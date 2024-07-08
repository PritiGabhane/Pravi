package Quiz;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pravi.ExerciseAnswers_Page;

public class SolveQuiz_Page {
	WebDriver driver;
	String Size;
	ExerciseAnswers_Page EA;
	StartQuiz_Page SP;

	@FindBy(xpath = "//span[@class = 'pyui-display'][1]")
	WebElement Weekly_Quizzes;

	@FindBy(xpath = "//span[@class = 'pyui-display'][2]")
	WebElement Monthly_Quizzes;

	@FindBy(xpath = "//*[text() = 'Start']")
	WebElement Startbtn;
	
	@FindBy(xpath = "//span[text()='Next']")
	WebElement Next;

	@FindBy(xpath = "//button[@class = 'button primary small' and text() = 'Finish']")
	WebElement FinishBtn;
	
	@FindBy(xpath = "//button[@class = 'button primary small' and text() = 'Complete']")
	WebElement CompleteBtn;

	public SolveQuiz_Page(WebDriver driver) {
		// Call the webdriver
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void CheckQuiz(String Quiz) 
	{
		if (Quiz.equals("Weekly Quizzes")) 
		{
			Size = Weekly_Quizzes.getText();
		} 
		else if (Quiz.equals("Monthly Quizzes"))
		{
			Size = Monthly_Quizzes.getText();
		}
		System.out.println("The total exercises are : " + Size);
	}
	
	@SuppressWarnings("deprecation")
	public void StartQuiz()
	{
		// Start the Quiz
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Startbtn.click();
	}

	//@Test
	public void Solve_Quiz() throws Exception
	{
		for(int i = 1; i < Integer.parseInt(Size); i++) 
		{
			VisitExercise_method();
			new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(Next)).click();
		}
		new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(FinishBtn)).click();
		new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.elementToBeClickable(CompleteBtn)).click();
	}
	
	//@Test
	public void VisitExercise_method() throws Exception
	{
		EA = new ExerciseAnswers_Page(driver);
		EA.API_Answers();
		EA.Answers_UI();
	}

}
