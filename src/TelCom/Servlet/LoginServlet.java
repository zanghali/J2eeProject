package TelCom.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import TelCom.Dao.Fabric.DaoFabric;
import TelCom.Dao.Instance.AdminDao;
import TelCom.Model.AdminModelBean;

/**  
 * Servlet implementation class LoginServlet
 */ @WebServlet("/login") 
 
 public class LoginServlet extends HttpServlet {  
	 
	 private static final long serialVersionUID = 1L; 
	 private AdminDao dao;     
	 
	 /** 
	  * @see HttpServlet#HttpServlet()    
	  */  
	 public LoginServlet() {      
		 super();
	}      
	 
	public void init() throws ServletException {   
		super.init();         
		//Verifie si le DAO existe dans l'espace de memoire partage entre les servlet 
		// si oui on les recupere, si non on le cree et on l'ajoute dans l'espace de memoire    
		//partage entre les servlet          
		if(getServletContext().getAttribute("DAO")!=null){     
			dao=(AdminDao)getServletContext().getAttribute("DAO");  
		}
		else {      
			dao = DaoFabric.getInstance().createAdminDao();  
			getServletContext().setAttribute("DAO",dao);  		
		}   
	}  

	 /**   
	  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
	  */ 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)        
			 throws ServletException, IOException {  
		 //Nothing to do 
	}  
	 
	 /**  
	  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)  
	  */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)           
			 throws ServletException, IOException {        

		AdminModelBean admin = dao.checkAdmin(	request.getParameter("login"),
												request.getParameter("password"));
		
		// Connexion réussie
		if( admin!=null){
			// Ajout à la mémoire session
			HttpSession session = request.getSession(true);
			session.setAttribute("myAdmin", admin);
		}
		
		// Rediriger la page courante vers la page d'accueil
		response.sendRedirect(request.getContextPath() + "/vues/admin.jsp");
	 }  
}