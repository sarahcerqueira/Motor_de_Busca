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

public class HistoricAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 5590830L;
	private Map<String, Object> session;
	private static InterfaceServerRMI servidor;

	
	public String execute() {

			try {
				servidor = RMIConection.rmi();
				ArrayList<String> s = servidor.getHistoric((String)session.get("username"));
				this.setHistoricBeans(new HistoricBean(s));
				System.out.println("action historic " + s.size());

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
		System.out.println("get historic");

		if(!session.containsKey("historicBean"))
			try {
				this.setHistoricBeans(new HistoricBean(servidor.getHistoric((String)session.get("username"))));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return (HistoricBean) session.get("historicBean");
			
	}

	public void setHistoricBeans(HistoricBean historicBean) {
			session.put("historicBean", historicBean);

	}
	


	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
}
