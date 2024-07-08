package API_UI;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
//import org.testng.annotations.Test;

public class ExerciseCode_Page {

	WebDriver driver;
	int length;
	String ExerciseCode = "", APICode, ECode;
	APIResponseCode_Page AC;

	@FindBy(xpath = "//div[@class=\"view-line\"]")
	WebElement Exercise_Code;

	public ExerciseCode_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//@Test
	public void Get_Code_UI() throws Exception
	{
		//Select and copy exercise code
		Actions action = new Actions(driver);
		action.keyDown(Exercise_Code, Keys.CONTROL).sendKeys("ac").keyUp(Keys.CONTROL).build().perform();

		//Paste text from clipboard
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		ECode = (String) clipboard.getData(DataFlavor.stringFlavor);
	}

	//@Test
	public void Code_UI() throws Exception
	{
		Get_Code_UI();
		char[] array = ECode.toCharArray();
		length = array.length;
		for(int i=0; i<length; i++)
		{	
			if(array[i] == 92 && (array[i+1] == 110 || array[i+1] == 114 || array[i+1] == 116))
			{
				i++;
				continue;
			}
			else if(array[i] != 92)
			{
				ExerciseCode += array[i];
			}
		}

		//remove spaces from string
		ExerciseCode = ExerciseCode.replaceAll("\\s", "");
	
		//return ExerciseCode;
	}

	//@Test
	public void Validate_Code()   
	{
		AC = new APIResponseCode_Page(driver);
		APICode = AC.Code_API();
		System.out.println("ExerciseCode = " + ExerciseCode);
		System.out.println("APICode = " + APICode);

		if(ExerciseCode.equals(APICode))
		{
			System.out.println("API and UI Code are same");
		}
		else
		{
			System.out.println("API and UI Code are not same");
			driver.quit();
		}
	}


}