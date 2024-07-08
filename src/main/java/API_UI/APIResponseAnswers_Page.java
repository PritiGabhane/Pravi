package API_UI;

//import io.restassured.response.Response;
//import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class APIResponseAnswers_Page {
	Object AStr = "";
	String EID;
//	ExerciseID_Page EI;
	WebDriver driver;
	
	public APIResponseAnswers_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//@Test
	public HashMap<String,String> Answers_API() throws SQLException
	{
//		EI = new ExerciseID_Page(driver);
//		EID = EI.Exercise_Id();
	
		String URL = "jdbc:postgresql://pravinyam-dev-do-user-9198634-0.b.db.ondigitalocean.com:25060/defaultdb";
		String UserName = "qauser";
		String Password = "priti@123pass$";
		
		HashMap<String, String> objMap = new HashMap<String, String>();
		
		//Establish connection
		Connection con = DriverManager.getConnection(URL, UserName, Password);
		
		//Create statement object
		Statement stmt = con.createStatement();
		
		//Execute Query
		String Query = "SELECT key, answer FROM pravinyam.cqadata where exid = '"+EID+"' ORDER BY exid ASC ";
		
		//Read and store data
		ResultSet result = stmt.executeQuery(Query);
		
		while(result.next())
		{
			String key = result.getString("key");
			String answer = result.getString("answer");
			objMap.put(key, answer);
//			System.out.println(key + " - " + answer);
		}
			
		//Close connection
		con.close();
		System.out.println("Connection closed");
		
//		System.out.println(objMap);
		return objMap;
	}
	
}