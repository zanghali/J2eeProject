package TelCom.Dao.Instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import TelCom.Model.AdminModelBean;

public class AdminDao { 
	
	 private Connection connection;
	 private String dB_HOST;  
	 private String dB_PORT;  
	 private String dB_NAME;  
	 private String dB_USER;  
	 private String dB_PWD;  
	 
	 public AdminDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
		 dB_HOST = DB_HOST;   
		 dB_PORT = DB_PORT;   
		 dB_NAME = DB_NAME;   
		 dB_USER = DB_USER;   
		 dB_PWD = DB_PWD;  
	}  
	 
	public AdminModelBean checkAdmin(String login,String password){
		
		AdminModelBean admin=null;
		
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
			+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			
			java.sql.Statement query =  connection.createStatement();
			java.sql.ResultSet rs = query.executeQuery
					( "SELECT * FROM admins WHERE login='"+login+"' AND password='"+password+"';");
			
			while (rs.next()) {
					admin=new AdminModelBean(	rs.getString("login"),
												rs.getString("password"));
			}		
			connection.close();
			
		} catch (SQLException e) {
				e.printStackTrace();
		}

		return admin;
	}
} 
