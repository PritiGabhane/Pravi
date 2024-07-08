package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UIReader{
	
	private Properties properties;
	private final String propertyFilePath= "configs//UI.properties";

	
	public UIReader(){
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
	
	public String getUrl() 
	{
		String Url = properties.getProperty("Url");
		if(Url != null) return Url;
		else throw new RuntimeException("Url not specified in the Configuration.properties file.");
	}
	
	public String getTitle() 
	{
		String Title = properties.getProperty("Title");
		if(Title != null) return Title;
		else throw new RuntimeException("Title not specified in the Configuration.properties file.");
	}
	
	public String getLoginUrl() 
	{
		String LoginUrl = properties.getProperty("LoginUrl");
		if(LoginUrl != null) return LoginUrl;
		else throw new RuntimeException("LoginUrl not specified in the Configuration.properties file.");
	}
	
	public String getValidUserID() 
	{
		String UserID = properties.getProperty("UserID");
		if(UserID != null) return UserID;
		else throw new RuntimeException("UserID not specified in the Configuration.properties file.");
	}
	
	public String getInvalidUserID() 
	{
		String Invalid_UserID = properties.getProperty("Invalid_UserID");
		if(Invalid_UserID != null) return Invalid_UserID;
		else throw new RuntimeException("Invalid_UserID not specified in the Configuration.properties file.");
	}
	
	public String getValid_Pass() 
	{
		String Valid_Pass = properties.getProperty("Valid_Pass");
		if(Valid_Pass != null) return Valid_Pass;
		else throw new RuntimeException("Valid_Pass not specified in the Configuration.properties file.");
	}
	
	public String getInvalid_Pass() 
	{
		String Invalid_Pass = properties.getProperty("Invalid_Pass");
		if(Invalid_Pass != null) return Invalid_Pass;
		else throw new RuntimeException("Invalid_Pass not specified in the Configuration.properties file.");
	}
	
	public String getNew_Pass() 
	{
		String New_Pass = properties.getProperty("New_Pass");
		if(New_Pass != null) return New_Pass;
		else throw new RuntimeException("New_Pass not specified in the Configuration.properties file.");
	}
	
	public String getName()
	{
		String Name = properties.getProperty("Name");
		if(Name != null) return Name;
		else throw new RuntimeException("Name not specified in the Configuration.properties file.");
	}
	
	public String getEmail()
	{
		String Email = properties.getProperty("Email");
		if(Email != null) return Email;
		else throw new RuntimeException("Email not specified in the Configuration.properties file.");
	}
	
	public String getMobile()
	{
		String Mobile = properties.getProperty("Mobile");
		if(Mobile != null) return Mobile;
		else throw new RuntimeException("Mobile not specified in the Configuration.properties file.");
	}
	
	public String getMessage()
	{
		String Message = properties.getProperty("Message");
		if(Message != null) return Message;
		else throw new RuntimeException("Message not specified in the Configuration.properties file.");
	}
	
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
	
}

