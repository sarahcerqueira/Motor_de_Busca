package view;

import model.ServerRMI;

import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/** Classe principal do servidor RMI. Permite que clientes RMI acessem o servidor.
 */
public class MainServerRMI {

	public static void main(String[] args) {
				
         try {
			 try {
				 
				 
			    	Registry registry = LocateRegistry.getRegistry();
			    	
					registry.bind("ServerRMI", new ServerRMI());
					System.out.println("Servidor RMI ativo");
				 
			    	
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

	}

}
