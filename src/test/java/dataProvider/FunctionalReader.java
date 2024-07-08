package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FunctionalReader{
	
	private Properties properties;
	private final String propertyFilePath= "configs//Functional.properties";

	
	public FunctionalReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	//Pravi variables
	public String getUrl() 
	{
		String Url = properties.getProperty("Url");
		if(Url != null) return Url;
		else throw new RuntimeException("Url not specified in the Configuration.properties file.");
	}
	
	public String getLoginUrl() 
	{
		String LoginUrl = properties.getProperty("LoginUrl");
		if(LoginUrl != null) return LoginUrl;
		else throw new RuntimeException("LoginUrl not specified in the Configuration.properties file.");
	}
	
	public String getUserID() 
	{
		String UserID = properties.getProperty("UserID");
		if(UserID != null) return UserID;
		else throw new RuntimeException("UserID not specified in the Configuration.properties file.");
	}
	
	public String getPass() 
	{
		String Password = properties.getProperty("Password");
		if(Password != null) return Password;
		else throw new RuntimeException("Password not specified in the Configuration.properties file.");
	}
	
	//Exercise Variables
	public String getTrack() 
	{
		String Track = properties.getProperty("Track");
		if(Track != null) return Track;
		else throw new RuntimeException("Track not specified in the Configuration.properties file.");
	}
	
	public String getModule() 
	{
		String Module = properties.getProperty("Module");
		if(Module != null) return Module;
		else throw new RuntimeException("Module not specified in the Configuration.properties file.");
	}
	
	public String getUserAction() 
	{
		String UserAction = properties.getProperty("UserAction");
		if(UserAction != null) return UserAction;
		else throw new RuntimeException("UserAction not specified in the Configuration.properties file.");
	}
	
	public String getPage() 
	{
		String Page = properties.getProperty("Page");
		if(Page != null) return Page;
		else throw new RuntimeException("Page not specified in the Configuration.properties file.");
	}
	
	public String getExercise() 
	{
		String Exercise = properties.getProperty("Exercise");
		if(Exercise != null) return Exercise;
		else throw new RuntimeException("Exercise not specified in the Configuration.properties file.");
	}
	
	//Quiz Variables
	public String getQuizType() 
	{
		String QuizType = properties.getProperty("QuizType");
		if(QuizType != null) return QuizType;
		else throw new RuntimeException("QuizType not specified in the Configuration.properties file.");
	}
	
	public String getQuizName() 
	{
		String QuizName = properties.getProperty("QuizName");
		if(QuizName != null) return QuizName;
		else throw new RuntimeException("QuizName not specified in the Configuration.properties file.");
	}
}
