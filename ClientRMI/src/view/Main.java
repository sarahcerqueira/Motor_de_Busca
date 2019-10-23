package view;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import model.InterfaceServerRMI;

public class Main {


	public static void main(String[] args) {
		InterfaceServerRMI servidor;
		
		try {
			servidor = (InterfaceServerRMI) Naming.lookup("rmi://127.0.0.1/ServerRMI");
			servidor.registerUser("sarah", "1234");
			if(servidor.login("Sarah", "1234")) {
				System.out.println("Retornou verdadeiro");
			}
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
