package model;

import java.io.IOException;

import control.Controler;

public class Cliente implements Runnable {
	private Controler system;
	private String[] request;
	private int port;
	private int ipClient;
	private int portClient;
	
	public Cliente(Controler system, int port, int ipClient, int portClient, String[] request){
		this.system = system;
		this.port = port;
		this.ipClient = ipClient;
		this.portClient = portClient;
		this.request = request;
		
		
	}
	@Override
	public void run() {
		
		
		String answer = null;
		
		//Verifica o tipo de solicitação e manda para o controler
		switch(request[0]) {
		case("login"):
			answer = system.login(request);
			break;
		
		case("useRegistry"):
			answer = system.useRegistry(request);
		break;
		
		case("changeUserPermission"):
			answer = system.changeUserPermission(request);
			break;
			
		case("userIsAdmin"):
			answer = system.userIsAdmin(request);
			break;
		
		case("getHistoric"):
			answer = system.getHistoric(request);
			break;
		
		case("addHistoric"):
			system.addHistoric(request);
			break;
		
		case("userHasNotification"):
			answer = system.userHasNotification(request);
			break;
			
		case("getUserNotification"):
			answer = system.getUserNotification(request);
			break;
		
		case("removeUserNotification"):
			system.removeUserNotification(request);
			answer = null;
			break;
		
		case("indexURL"):
			try {
				system.indexURL(request);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answer= null;
			break;
		
		case("getImportantPages"):
			answer = system.getImportantPages();
			break;
		
		case("getImportantSearch"):
			answer = system.getImportantSearch();
			break;
			
		case("search"):
			answer = system.search(request);
			break;
		
		}
		
	}

}
