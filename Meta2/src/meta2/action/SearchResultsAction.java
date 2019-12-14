package meta2.action;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import model.HistoricBean;
import model.RMIConection;
import rmiInterface.InterfaceServerRMI;

public class SearchResultsAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 5590830L;
	private static InterfaceServerRMI servidor;
	private Map<String, Object> session;
	private String word;
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getWord() {
		return word;
	}

	
	public String execute() {

			try {
				servidor = RMIConection.rmi();
				ArrayList<String> s = servidor.search("Coimbra");
				System.out.println("Hace execute");
				//System.out.println(s);
				this.setSearchRBean(new HistoricBean(s));
				System.out.println("action searchresults: " + s.size());

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
		
			return SUCCESS;
	}
	
	

	public HistoricBean getHistoricBean() {
		System.out.println("get search");

		if(!session.containsKey("searchRBean"))
			try {
				this.setSearchRBean(new HistoricBean(servidor.search("Coimbra")));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return (HistoricBean) session.get("searchRBean");
			
	}

	public void setSearchRBean(HistoricBean searchRBean) {
			System.out.println("Settt");
			session.put("searchRBean", searchRBean);
			System.out.println("Settt2");
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	
}
