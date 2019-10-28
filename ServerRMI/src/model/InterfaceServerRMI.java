package model;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/** Interface RMI do servidor.
 */
public interface InterfaceServerRMI extends Remote {
	
	//Funionalidades relacionadas ao usuario
	public boolean login(String username, String password) throws RemoteException, IOException;
	public boolean registerUser(String username, String password) throws RemoteException, IOException;
	public boolean changeUserPermission(String username) throws RemoteException, IOException;
	public ArrayList<Story> getHistoric (String username)throws RemoteException, IOException;
	public void addHistoric(String username,String date, String hour, Site site ) throws RemoteException;
	public boolean userHasNotification(String username) throws RemoteException, IOException;
	public void removeUserNotification(String username) throws RemoteException, IOException;
	public boolean userIsAdmin(String username)throws RemoteException, IOException;
	public String getUserNotification(String username)throws RemoteException, IOException;
	
	//Funcionalidades relacionadas ao administrador
	public List<Entry<String, Integer>> getImportantPages() throws RemoteException, IOException;
	public List<Entry<String, Integer>> getImportantSearch() throws RemoteException, IOException;
	public void getServerMulticastActive()throws RemoteException;
	void indexURL(String url) throws RemoteException, IOException;
	
	
}
