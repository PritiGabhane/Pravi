package API_UI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
//import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

public class APIResponseCode_Page {
	Object str, string;
	int length;
	WebDriver driver;
	String EID, Ecode, code, APICode = "";
//	ExerciseID_Page EI;

	public APIResponseCode_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//@Test
	public void Get_Code_API()
	{
//		EI = new ExerciseID_Page(driver);
//		EID = EI.Exercise_Id();
		//EID = "CBTV1";

		baseURI = "https://dolphin-app-uzion.ondigitalocean.app/";
		Response res = given()
				.contentType("content-type: application/json; charset=utf-8")
				.pathParam("qpath", "code")
				.queryParam("exercise", EID)
				.queryParam("clientpublic", "baef7468287a44d8ac3634026d9fb8d1")
				.queryParam("clientsecret", "181a2f3171117c1bd164c88b1171c1b83114fc1712121b12")

				.when()
				.get("api/{qpath}");

		str = res.body().asString();
		//System.out.println(str);
	}

	//@Test
	public String Code_API()
	{
		Get_Code_API();
		Ecode = (String) str;
		code = Ecode.substring(1, Ecode.length() - 1);
		char[] array = code.toCharArray();
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
				APICode += array[i];
			}
		}

		APICode = APICode.replaceAll("\\s", "");
		//System.out.println(APICode);
		return APICode;
	}
}
