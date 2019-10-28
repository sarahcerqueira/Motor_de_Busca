package model;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import model.InterfaceServerRMI;

public class ServerRMI extends UnicastRemoteObject implements InterfaceServerRMI {
	
	private ClienteUDP serverComunication;

	public ServerRMI() throws RemoteException, SocketException, UnknownHostException {
		serverComunication = new  ClienteUDP();
	}

	
	public boolean login(String username, String password) throws RemoteException, IOException {
		String request = "login|username;"+username + "|password;"+password+"|";
		String answer = this.makeRequest(request);
			
		if(answer.split("\\|")[1].equals("true")) {
			return true;
		}
		
		return false;
	}

	
	public boolean registerUser(String username, String password) throws IOException {
		String request = "useRegistry|username;"+username + "|password;"+password+"|";
		String answer = this.makeRequest(request);
				
		if(answer.split("\\|")[1].equals("true")) {
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
	public ArrayList<Story> getHistoric(String username) throws IOException {
		String request = "getHistoric|username;"+username + "|";
		String answer = this.makeRequest(request);
		int size = Integer.parseInt(answer.split("\\|")[1].split(";")[1]);
		String [] historic = answer.split("|");
		
		
		return null;
	}


	@Override
	public void addHistoric(String username, String date, String hour, Site site) throws RemoteException {
		
		
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
	public List<Entry<String, Integer>> getImportantPages() throws IOException {
		String request = "getImportantPages|";
		String answer = this.makeRequest(request);

		return null;
	}


	@Override
	public List<Entry<String, Integer>> getImportantSearch() throws IOException {
		String request = "getImportantSearch|";
		String answer = this.makeRequest(request);

		return null;
	}


	@Override
	public void getServerMulticastActive() throws RemoteException {
		// TODO Auto-generated method stub
		
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
		
		byte[] buf = new byte[1024];
		buf = serverComunication.acceptPacket(buf);
		return new String(buf);

	}


}
