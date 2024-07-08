package API_UI;

import java.sql.*;
import java.util.*;

public class JDBCPostgreSQLConnect {
	
	static String URL = "jdbc:postgresql://pravinyam-dev-do-user-9198634-0.b.db.ondigitalocean.com:25060/defaultdb";
	static String UserName = "qauser";
	static String Password = "priti@123pass$";
	
	public static void main(String[] args) throws SQLException
	{
        HashMap<Integer, String[]> map = new HashMap<>();
		
		//Establish connection
		Connection con = DriverManager.getConnection(URL, UserName, Password);
		
		//Create statement object
		Statement stmt = con.createStatement();
		
		//Execute Query
		String Query = "SELECT key, answer, type FROM pravinyam.cqadata where exid = 'CMCP21' ORDER BY exid ASC";
		
		//Read and store data
		ResultSet result = stmt.executeQuery(Query);
		
		while(result.next())
		{
			int key = result.getInt("key");
			String answer = result.getString("answer");
			String type = result.getString("type");
			map.put(key, new String[] {answer, type});
			
//			System.out.println(key + " - " + answer + " - " + type);	
		}
		 for (Integer i : map.keySet()) 
		 {
			 String Answer = map.get(i)[0];
			 String Type = map.get(i)[1];
			 System.out.println(i + " - " + Answer + " - " + Type);	     
		 }
		
		//Close connection
		con.close();
		System.out.println("Connection closed");
	}

}
