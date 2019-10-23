package model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import model.InterfaceServerRMI;

public class ServerRMI extends UnicastRemoteObject implements InterfaceServerRMI {
	
	private ArrayList<String> usuarios; 

	public ServerRMI() throws RemoteException {
		usuarios = new ArrayList<String>();
		
		// TODO Auto-generated constructor stub
	}

	
	public boolean login(String username, String password) throws RemoteException {
		System.out.println("Usuário "+ username+ " fez login");
		
		return true;
	}

	
	public void registerUser(String username, String password) throws RemoteException {
		System.out.println("Usuário "+ username+ " se registrou");
		usuarios.add(username);

		
	}

}
