package teste.action;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.opensymphony.xwork2.ActionSupport;

import model.InterfaceServerRMI;

public class TesteAction extends ActionSupport {
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
			servidor = (InterfaceServerRMI) Naming.lookup("server");
			
			if(servidor.login(username, password)) {
				return SUCCESS;
			} else {
				return ERROR;
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return ERROR;
}

}
