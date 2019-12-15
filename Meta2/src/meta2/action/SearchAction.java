package meta2.action;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import model.Bean;
import model.RMIConection;
import model.SiteBean;
import rmiInterface.InterfaceServerRMI;

public class SearchAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 4L;
	private static InterfaceServerRMI servidor;
	private String word;
	private String url;
	private Map<String, Object> session;

	public String execute() {
		try {
			servidor = RMIConection.rmi();
			ArrayList<String> search;
			search = servidor.search(word);

			if(search.isEmpty()) 
				search.add("Não há resultados para essa pesquisa =(");

			this.setBeans(search);			
			return SUCCESS;

			
		} catch (NotBoundException | IOException e) {
			System.out.println("Erro ao se conectar com o servidor RMI");
		} 

		
		return ERROR;
      }
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getWord() {
		return word;
	}
	
	public SiteBean getSiteBean() {

		if(!session.containsKey("search"))
			try {
				this.setBeans((servidor.search(word)));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return (SiteBean) session.get("search");
			
	}
	
	public ArrayList<SiteBean> getSite(ArrayList<String> text){
		ArrayList<SiteBean> sites = new ArrayList<SiteBean>();
		
		for(int i =0; i< text.size(); i=i+3) {
			SiteBean b = new SiteBean();
			
			b.setTitle(text.get(i));
			b.setUrl(text.get(i+1));
			b.setText(text.get(i+2));
			b.setNum(i/3+1);
			
			sites.add(b);
		}
		
		return sites;
		
	}
	
	public String add() throws NumberFormatException, RemoteException, IOException{
		
		if(session.containsKey("username"))
			servidor.getSite((String)session.get("username"), Integer.parseInt(url));
		else
			servidor.getSite(null, Integer.parseInt(url));

		
		return SUCCESS;
		
	}

	public void setBeans( ArrayList<String> word) {
		ArrayList<SiteBean> b = getSite(word);
			session.put("search",b );
			session.put("qtdsearch",b.size() );


	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
