package API_UI;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
//import org.testng.annotations.Test;

public class ExerciseQuestions_Page {
	WebDriver driver;
	String Questions, ExerciseQuestions, APIQuestions;
	APIResponseQuestions_page AQ;

	@FindBy(xpath = "//label[@class = 'form-label']")
	List<WebElement> Exercise_Questions;

	public ExerciseQuestions_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//@Test
	public void Questions_UI()
	{
		for(int i=1; i<Exercise_Questions.size() + 1; i++)
		{
			Questions = Exercise_Questions.get(i).getText();
			ExerciseQuestions += Questions.substring(4);
		}
		ExerciseQuestions = ExerciseQuestions.substring(4);
	}
	
	//@Test
	public void Validate_Questions()   
	{
		AQ = new APIResponseQuestions_page(driver);
		APIQuestions = AQ.Questions_API();
		System.out.println("ExerciseQuestions = " + ExerciseQuestions);
		System.out.println("APIQuestions = " + APIQuestions);
		
		if(ExerciseQuestions.equals(APIQuestions))
		{
			System.out.println("API and UI questions are same");
		}
		else
		{
			System.out.println("API and UI questions are not same");
			driver.quit();
		}
	}
}