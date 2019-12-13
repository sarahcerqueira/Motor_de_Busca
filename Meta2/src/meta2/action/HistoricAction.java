package meta2.action;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import model.RMIConection;
import model.Story;
import rmiInterface.InterfaceServerRMI;

public class HistoricAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 5590830L;
	private Map<String, Object> session;
	private static InterfaceServerRMI servidor;
	private ArrayList<Story> historic;
	
	public String execute() {
		try {
			servidor = RMIConection.rmi();
			session.put("loggedin", true);
			setHistoric(servidor.getHistoric((String)session.get("username")));
			//session.put("historicresults", "Olaaaaa");
			return SUCCESS;
		} catch (NotBoundException | IOException e) {
			System.out.println("Error at conect with servidor RMI");
			//e.printStackTrace();
		} 
		return ERROR;
	}

	private void setHistoric(ArrayList<String> h) {
		historic = new ArrayList<Story>();
		
		for(int i =0; i<h.size(); i=i+2) {
			historic.add(new Story(h.get(i), h.get(i+1)));
			
		}
		
		
	}

	@Override
	public void setSession(Map<String, Object> arg) {
		this.session = session;
	}
	
	public  ArrayList<Story> getHistoric(){
		return historic;
	}
}
