package TelCom.Dao.Fabric;

import TelCom.Dao.Instance.AdminDao;

public final class DaoFabric {  
	// L'utilisation du mot clé volatile permet, en Java version 5 et sup�rieur, 
	// permet d'eviter le cas où "Singleton.instance" est non-nul,
	// mais pas encore "réellement" instancié. 
	// De Java version 1.2 à 1.4, il est possible d'utiliser la classe 
	// ThreadLocal.
	private static volatile DaoFabric instance = null;  
	
	private static final String DB_HOST="localhost";
	private static final String DB_PORT="3306";
	private static final String DB_NAME="projetmajeur";
	private static final String DB_USER="root";
	private static final String DB_PWD="";
	 
	private DaoFabric() {  
		super(); 
		 
		try {   
			// Chargement du Driver, puis �tablissement de la connexion  
			Class.forName("com.mysql.jdbc.Driver");  
		}catch (ClassNotFoundException e) { 
			e.printStackTrace(); 
		}
	}  
	 
	/** 
	 * Methode permettant de renvoyer une instance de la classe Singleton 
     * @return Retourne l'instance du singleton. 
     */  public final static DaoFabric getInstance() { 
    	 	// Le "Double-Checked Singleton"/"Singleton doublement verifié permet   
	    	// d'eviter un appel couteux à synchronized, 
	    	// une fois que l'instanciation est faite.   
	    	if (DaoFabric.instance == null) { 
	    		  
	    		// Le mot-clé synchronized sur ce bloc empêche toute instanciation   
	    		// multiple m�me par differents "threads".  
	    		synchronized (DaoFabric.class) {    
	    			if (DaoFabric.instance == null) {
	    				DaoFabric.instance = new DaoFabric();    
	    			}  
	    		}
	    	}
	    	  
	    	return DaoFabric.instance;  
     	}  
	  
     public AdminDao createAdminDao() {
    	 AdminDao adminDao = new
    			 AdminDao(this.DB_HOST,this.DB_PORT,this.DB_NAME,this.DB_USER,this.DB_PWD);  
		 return adminDao;  
	} 
     
}