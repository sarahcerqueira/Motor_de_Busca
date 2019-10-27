package control;

import model.UCBusca;

public class Controler {
	private UCBusca ucBusca;
	
	public Controler() {
		ucBusca = new UCBusca();
	}
	
	
	public String login (String [] login) {
		String username = login[1].split(";")[1];
		String password = login[2].split(";")[1];
		
		if(ucBusca.login(username, password)) {
			return "login|true|";
		}
		return "login|false|";
		
	}
	
	public String useRegistry (String[] registry) {
		String username = registry[1].split(";")[1];
		String password = registry[2].split(";")[1];
		
		System.out.print(username +":"+ password);
		
		if(ucBusca.useRegitry(username, password)) {
			return "useRegistry|true|";
		}
		
		return "useRegistry|false|";

		
	}

}
