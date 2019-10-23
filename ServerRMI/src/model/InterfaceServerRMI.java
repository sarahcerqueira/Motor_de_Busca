package model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceServerRMI extends Remote {
	
	public boolean login(String username, String password) throws RemoteException;
	public void registerUser(String username, String password) throws RemoteException;



}
