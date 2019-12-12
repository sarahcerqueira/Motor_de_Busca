package model;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rmiInterface.InterfaceServerRMI;

public class RMIConection {
	private static InterfaceServerRMI servidor;

	public static InterfaceServerRMI rmi() throws MalformedURLException, RemoteException, NotBoundException {
		if(servidor == null) {
			servidor = (InterfaceServerRMI) Naming.lookup("rmi://10.101.210.5:1015/server");
		}
		
		return  servidor;

	}
}
