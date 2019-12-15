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

public class HistoricAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 5590830L;
	private Map<String, Object> session;
	private static InterfaceServerRMI servidor;

	
	public String execute() {

			try {
				servidor = RMIConection.rmi();
				ArrayList<String> s = servidor.getHistoric((String)session.get("username"));
				s = this.trata(s);
				this.setBean(new Bean(s));

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
	
	

	public Bean getBean() {

		if(!session.containsKey("historicBean"))
			try {
				this.setBean(new Bean(servidor.getHistoric((String)session.get("username"))));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return (Bean) session.get("historicBean");
			
	}

	public void setBean(Bean historicBean) {
			session.put("historicBean", historicBean);

	}
	
	public ArrayList<String> trata (ArrayList<String> s ){
		ArrayList<String> novo = new ArrayList<String>();
		
		for(int i =0; i<s.size(); i=i+2) {
			novo.add(s.get(0)+"\n"+s.get(i+1));
		}
		
		return novo;
		
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
}
