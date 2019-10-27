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
	
	private ArrayList<String> usuarios; 
	private ClienteUDP serverComunication;

	public ServerRMI() throws RemoteException, SocketException, UnknownHostException {
		usuarios = new ArrayList<String>();
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
		
		System.out.print(answer.split("\\|")[1]);
		
		if(answer.split("\\|")[1].equals("true")) {
			return true;
		}
		
		return false;
		
	}


	@Override
	public boolean changeUserPermission(String username, boolean permission) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public ArrayList<Story> getHistoric(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void addHistoric(String username, String date, String hour, Site site) throws RemoteException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addNotification(String username, String notification) throws RemoteException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean userHasNotification(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void removeUserNotification(String username) throws RemoteException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void indexURL() throws RemoteException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Entry<String, Integer>> getImportantPages() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Entry<String, Integer>> getImportantSearch() throws RemoteException {
		// TODO Auto-generated method stub
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
	
	public String waitAnswer() throws IOException {
		
		byte[] buf = new byte[1024];
		buf = serverComunication.acceptPacket(buf);
		return new String(buf);

	}
}
