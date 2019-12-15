package meta2.action;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import model.RMIConection;
import rmiInterface.InterfaceServerRMI;

public class LoginAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 4L;
	private static InterfaceServerRMI servidor;
	private String username;
	private String password;
	private Map<String, Object> session;

	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String execute() {
		try {
			servidor = RMIConection.rmi();
			
			if(servidor.login(username, password)) {
				System.out.println(":)");
				session.put("username", username);
				session.put("password", password);
				session.put("loggedin", true);
				
				if(servidor.userIsAdmin(username))
					session.put("admin", true);


								
				return SUCCESS;
			} else {
				System.out.println("LOL2");
				return ERROR;
			}
			
			
		} catch (NotBoundException | IOException e) {
			System.out.println("Erro ao se conectar com o servidor RMI");
			//e.printStackTrace();
		} 

		
		return ERROR;
}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
