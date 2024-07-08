package Pravi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.testng.annotations.Test;

public class ExerciseID_Page {
	WebDriver driver;
	String ExerciseID, EID;
	
	@FindBy(xpath = "//span[@class = 'editor-title-pre']") WebElement Exercise_ID;
	
//	@FindBy(xpath = "//h3[@class = 'editor-title']") WebElement Exercise_ID;

	public ExerciseID_Page(WebDriver driver)
	{ 
		//Call to webdriver
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//@Test
	public String Exercise_Id()
	{
		//Check for the current ExerciseId
		ExerciseID = Exercise_ID.getText();
		if(ExerciseID.contains("-"))
		{
			String[] strings = ExerciseID.split("-");
			EID = strings[0];
		}
		else if(ExerciseID.contains("| "))
		{
			String[] strings = ExerciseID.split("\\| ");
			EID = strings[1];
		}
//		System.out.println(EID);
		return EID;
//		return ExerciseID;
	}
}
