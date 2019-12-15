package meta2.action;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import model.Bean;
import model.RMIConection;
import rmiInterface.InterfaceServerRMI;

public class T10WordsAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 4L;
	private static InterfaceServerRMI servidor;
	private Map<String, Object> session;
	
	public String execute(){
		try {
			servidor = RMIConection.rmi();
			ArrayList<String> words = servidor.getImportantSearch();
			System.out.print(words);

			words = trata(words);
			this.setBean(new Bean(words));
			
			System.out.print(words);
			
			return SUCCESS;
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
	
	public ArrayList<String> trata (ArrayList<String> s ){
		ArrayList<String> novo = new ArrayList<String>();
		
		for(int i =0; i<s.size(); i=i+2) {
			novo.add(s.get(i)+"\n"+s.get(i+1));
		}
		
		return novo;
		
	}
	
	public Bean getBean() {

		if(!session.containsKey("words"))
			try {
				ArrayList<String> words = servidor.getImportantPages();
				words = trata(words);
				this.setBean(new Bean(words));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return (Bean) session.get("words");
			
	}

	public void setBean(Bean bean) {
			session.put("words", bean);

	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}