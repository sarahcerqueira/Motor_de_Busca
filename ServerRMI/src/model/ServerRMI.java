package model;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import rmiInterface.InterfaceServerRMI;

/** Implementa os métodos remotos do servidor RMI. Cada método é uma solicitação ao servidor multicast.
 * Na implementação dos métodos o servidor RMI converte os dados de entrada para o protocolo do sistema e envia
 * via pacote UDP para o servidor multicast.
 */
public class ServerRMI extends UnicastRemoteObject implements InterfaceServerRMI {
	
	private ArrayList<String> sites;
	private ClienteUDP serverComunication;

	public ServerRMI() throws RemoteException, SocketException, UnknownHostException {
		serverComunication = new  ClienteUDP();
	}

	
	public boolean login(String username, String password) throws RemoteException, IOException {
		String request = "login|username;"+username + "|password;"+password+"|";
		String answer = this.makeRequest(request);
			
		if(answer.split("\\|")[1].split(";")[1].equals("true")) {
			return true;
		}
		
		return false;
	}

	
	public boolean registerUser(String username, String password) throws IOException {
		String request = "useRegistry|username;"+username + "|password;"+password+"|";
		String answer = this.makeRequest(request);
				
		if(answer.split("\\|")[1].split(";")[1].equals("true")) {
			return true;
		}
		
		return false;
		
	}

	@Override
	public boolean userIsAdmin(String username) throws RemoteException, IOException {
		String request = "userIsAdmin|username;"+username + "|";
		String answer = this.makeRequest(request);
		
		if(answer.split("\\|")[1].split(";")[1].equals("true")) {
			return true;
		}

		return false;
	}


	public boolean changeUserPermission(String username) throws IOException {
		String request = "changeUserPermission|username;"+username + "|";
		String answer = this.makeRequest(request);
		
		if(answer.split("\\|")[1].split(";")[1].equals("true")) {
			return true;
		}

		return false;
	}


	@Override
	public ArrayList<String> getHistoric(String username) throws IOException {
		String request = "getHistoric|username;"+username + "|";
		String[] answer = this.makeRequest(request).split("\\|");
		ArrayList<String> historic = new ArrayList<String>();
		
		if(answer.length <= 4) {
			return null;
		}
		
		for(int i=3; i<answer.length;i++) {
			if(answer[i].split(";").length >= 2) {
				String time = answer[i].split(";")[0];
				String url = answer[i].split(";")[1];
				historic.add(time);
				historic.add(url);}
			
		}
		
		return historic;
	}


	@Override
	public void addHistoric(String username, String time, String url) throws IOException {
		
		if(username == null) {
			username = "null";
		}
		
		String request = "addHistoric|username;"+username + "|time;"+time+"|url;"+url+"|";
		this.makeRequestNoAnswer(request);
		
	}
	


	@Override
	public boolean userHasNotification(String username) throws IOException {
		String request = "userHasNotification|username;"+username + "|";
		String answer = this.makeRequest(request);

		if(answer.split("\\|")[1].equals("true")) {
			return true;
		}
		return false;
	}
	
	@Override
	public String getUserNotification(String username) throws RemoteException, IOException {
		String request = "getUserNotification|username;"+username + "|";
		String answer = this.makeRequest(request);
		

		return answer.split("\\|")[1].split(";")[1];
	}


	@Override
	public void removeUserNotification(String username) throws IOException {
		String request = "removeUserNotification|username;"+username + "|";
		makeRequestNoAnswer(request);
	}


	@Override
	public void indexURL(String url) throws IOException {
		String request = "indexURL|url;"+url+ "|";
		makeRequestNoAnswer(request);

	}


	@Override
	public ArrayList<String> getImportantPages() throws IOException {
		String request = "getImportantPages|";
		String[] answer = this.makeRequest(request).split("\\|");
		ArrayList<String> importantPages = new ArrayList<String>();
		
		if(answer.length <= 3) {
			return null;
		}
		
		for(int i=3; i<answer.length; i++) {
			
			if(answer[i].split(";").length >= 2) {
				String url = answer[i].split(";")[0];
				String numAcesso = answer[i].split(";")[1];
				
				importantPages.add(url);
				importantPages.add(numAcesso);}
		}
		
		return importantPages;
	}


	@Override
	public ArrayList<String> getImportantSearch() throws IOException {
		String request = "getImportantSearch|";
		String[] answer = this.makeRequest(request).split("\\|");
		ArrayList<String> importantSearch = new ArrayList<String>();
		
		if(answer.length <= 3) {
			return null;
		}
		
		for(int i=3; i<answer.length; i++) {
			if(answer[i].split(";").length >= 2) {

				String search = answer[i].split(";")[0];
				String numAcesso = answer[i].split(";")[1];
				
				importantSearch.add(search);
				importantSearch.add(numAcesso);
			}
		}
		

		return importantSearch;
	}
	
	@Override
	public ArrayList<String> search(String search) throws IOException {
		String request = "search|"+search+"|";
		String[] answer = this.makeRequest(request).split("\\|");
		
		sites = new ArrayList<String>();
		
		
		for(int i=3; i<answer.length; i++) {
			if(answer[i].equals("") || !answer[i].contains(";") || answer[i].split(";").length<3 )
				continue;
			
			String title = answer[i].split(";")[0];
			String url = answer[i].split(";")[1];
			String text = answer[i].split(";")[2];

			
			sites.add(title);
			sites.add(url);
			sites.add(text);

		}
		

		return sites;
	}

	
	/** Envia uma solicitação aos sevidores multicast.
	 * 
	 * @param request		Mensagem de solicitação de acordo com o protocolo.
	 * @throws IOException	Exceção lançada caso haja algum problema ao enviar a solicitação. 	
	 */
	public String makeRequest(String request) throws IOException {
		serverComunication.sendPacket(request.getBytes());
		return waitAnswer();

	}
	
	/** Envia uma solicitação aos sevidores multicast.
	 * 
	 * @param request		Mensagem de solicitação de acordo com o protocolo.
	 * @throws IOException	Exceção lançada caso haja algum problema ao enviar a solicitação. 	
	 */
	public void makeRequestNoAnswer(String request) throws IOException {
		serverComunication.sendPacket(request.getBytes());

	}
	
	public String waitAnswer() throws IOException {
		byte[] buf = serverComunication.acceptPacket();
		return new String(buf);

	}


	@Override
	public String getSite(String username, int index) throws IOException {
		index = index -1;
		System.out.println("Quantidade: "+sites.size() + " :" + sites.size()/3);
		if(sites.isEmpty() || sites.size()/3 < index)
			return null;
		
		this.addHistoric(username, getDateTime(), sites.get(index*3+1));
		
		return sites.get(index*3+1);//Posição 1 titulo, 2 url, 3 texto
	}
	
	private String getDateTime() { 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		Date date = new Date(); 
		return dateFormat.format(date); 
	}
	
	


	



	


	


	

}
