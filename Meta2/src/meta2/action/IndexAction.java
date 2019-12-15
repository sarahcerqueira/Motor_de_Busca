package meta2.action;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import model.RMIConection;
import rmiInterface.InterfaceServerRMI;

public class IndexAction extends ActionSupport implements SessionAware  {
	private static final long serialVersionUID = 4L;
	private static InterfaceServerRMI servidor;
	private String url;
	private Map<String, Object> session;

	
	public String execute(){
		try {
			System.out.println(url);
			servidor = RMIConection.rmi();
			servidor.indexURL(url);
			session.put("indexacao", true);
			
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
		
		session.put("indexacao", false);
		return ERROR;
		
	}
	
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
