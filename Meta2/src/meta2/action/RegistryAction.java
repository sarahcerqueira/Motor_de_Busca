package meta2.action;

import java.io.IOException;
import java.rmi.NotBoundException;

import com.opensymphony.xwork2.ActionSupport;

import model.RMIConection;
import rmiInterface.InterfaceServerRMI;

public class RegistryAction extends ActionSupport {
	
	private static final long serialVersionUID = 4L;
	private static InterfaceServerRMI servidor;
	private String username;
	private String password;

	
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
			
			if(servidor.registerUser(username, password)) {
				return SUCCESS;
			} else {
				return ERROR;
			}
			
			
		} catch (NotBoundException | IOException e) {
			System.out.println("Erro ao se conectar com o servidor RMI");
			//e.printStackTrace();
		} 

		
		return ERROR;
}


}
