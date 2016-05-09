package TelCom.Model;

import java.io.Serializable;

//contrainte BEAN implements Serializable
public class AdminModelBean implements Serializable{
	private String login="";
	private String password="";

	//Contrainte BEAN constructeur sans parametre
	public	AdminModelBean() {  }
	
	 public AdminModelBean(String login,String password)  { 
		 this.login = login;   
		 this.password = password;  
	} 

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String toString() {
		return  this.getLogin();
	}
}
