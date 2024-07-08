package API_UI;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
//import org.testng.annotations.Test;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class APIResponseQuestions_page {
	Object QStr = "";
	String label = "", EID;
//	ExerciseID_Page EI;
	JSONObject jo;
	WebDriver driver;

	public APIResponseQuestions_page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// @Test
	public void Get_Questions_API() {
//		EI = new ExerciseID_Page(driver);
//		EID = EI.Exercise_Id();

		baseURI = "https://dolphin-app-uzion.ondigitalocean.app/";

		Response res = given().contentType("content-type: application/json; charset=utf-8")
				.pathParam("qpath", "questions").queryParam("exercise", EID)
				.queryParam("clientpublic", "baef7468287a44d8ac3634026d9fb8d1")
				.queryParam("clientsecret", "181a2f3171117c1bd164c88b1171c1b83114fc1712121b12")

				.when()
				.get("api/{qpath}");

		jo = new JSONObject(res.asString());
	}

	// @Test
	public String Questions_API() {
		Get_Questions_API();
		for (int i = 0; i < jo.getJSONArray("questions").length(); i++) {
			label = label + jo.getJSONArray("questions").getJSONObject(i).get("label").toString();
		}
		// System.out.println(label);
		return label;
	}
}