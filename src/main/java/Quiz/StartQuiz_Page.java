package Quiz;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartQuiz_Page {
	WebDriver driver; String Size;

	@FindBy(xpath = "//*[@href='/tracks/Evaluations']")
	WebElement Evaluations;

	@FindBy(xpath = "//*[@class='pyui_exercises-status-tabs']//ul//li")
	List<WebElement> QuizType_list;

	@FindBy(xpath = "//*[@class='pyui-eval-list']")
	List<WebElement> Quiz_list;
	
	public StartQuiz_Page(WebDriver driver) {
		// Call to webdriver
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// @Test
	@SuppressWarnings("deprecation")
	public void ClickEvaluation() {
		// Select the Evaluation module
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Evaluations.click();
	}

	// @Test
	public void QuizType(String Type) 
	{
		// Select the QuizType
		System.out.println("QuizType values are : " + QuizType_list.size());
		
		for (int i = 0; i < QuizType_list.size(); i++) 
		{
			if (QuizType_list.get(i).getText().equals(Type)) 
			{
				// Selected Type and Click it
				System.out.println("Selected QuizType : " + QuizType_list.get(i).getText());
				QuizType_list.get(i).click();
			}
		}
	}

	// @Test
	public int SelectQuiz(String Quiz) 
	{
		// Select the Quiz and Start
		int count = 0;
		System.out.println("Quiz values are : " + Quiz_list.size());
		
		for (int i = 0; i < Quiz_list.size(); i++) 
		{
			if (Quiz_list.get(i).getText().equals(Quiz)) 
			{
				// Selected the Quiz
				System.out.println("Selected Quiz : " + Quiz_list.get(i).getText());
				Quiz_list.get(i).click();
				count++;
			} 
		}
		return count;
	}
	
	
}
