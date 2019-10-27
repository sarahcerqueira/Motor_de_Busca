package model;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;


public interface InterfaceServerRMI extends Remote {
	
	//Funionalidades relacionadas ao usuario
	public boolean login(String username, String password) throws RemoteException, IOException;
	public boolean registerUser(String username, String password) throws RemoteException, IOException;
	public boolean changeUserPermission(String username, boolean permission) throws RemoteException;
	public ArrayList<Story> getHistoric (String username)throws RemoteException;
	public void addHistoric(String username,String date, String hour, Site site ) throws RemoteException;
	public void addNotification(String username, String notification) throws RemoteException;
	public boolean userHasNotification(String username) throws RemoteException;
	public void removeUserNotification(String username) throws RemoteException;
	
	//Funcionalidades relacionadas ao administrador
	public void indexURL() throws RemoteException;
	public List<Entry<String, Integer>> getImportantPages() throws RemoteException;
	public List<Entry<String, Integer>> getImportantSearch() throws RemoteException;
	public void getServerMulticastActive()throws RemoteException;
	
	
}
