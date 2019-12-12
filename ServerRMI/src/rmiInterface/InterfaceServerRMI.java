package rmiInterface;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


/** Interface RMI do servidor.
 */
public interface InterfaceServerRMI extends Remote {
	
	//Funionalidades relacionadas ao usuario
	public boolean login(String username, String password) throws RemoteException, IOException;
	public boolean registerUser(String username, String password) throws RemoteException, IOException;
	public boolean changeUserPermission(String username) throws RemoteException, IOException;
	public ArrayList<String> getHistoric (String username)throws RemoteException, IOException;
	public ArrayList<String> search(String search)throws RemoteException, IOException;
	public boolean userHasNotification(String username) throws RemoteException, IOException;
	public void removeUserNotification(String username) throws RemoteException, IOException;
	public boolean userIsAdmin(String username)throws RemoteException, IOException;
	public String getUserNotification(String username)throws RemoteException, IOException;
	void addHistoric(String username, String time, String url) throws IOException;
	String getSite(String username, int index) throws RemoteException, IOException;
	
	//Funcionalidades relacionadas ao administrador
	public ArrayList<String> getImportantPages() throws RemoteException, IOException;
	public ArrayList<String> getImportantSearch() throws RemoteException, IOException;
	public void indexURL(String url) throws RemoteException, IOException;
	
	
	
	
}



