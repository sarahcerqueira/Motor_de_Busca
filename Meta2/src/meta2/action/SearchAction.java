package meta2.action;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import model.RMIConection;
import rmiInterface.InterfaceServerRMI;

public class SearchAction extends ActionSupport {
	private static final long serialVersionUID = 4L;
	private static InterfaceServerRMI servidor;
	private String word;
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getWord() {
		return word;
	}

	public String execute() {
		try {

			System.out.println("Hey");
			servidor = RMIConection.rmi();
			ArrayList<String> search;
			search = servidor.search(word);

			System.out.println("Word: " + search);
			
			if(search != null) {

				System.out.println("Word: " + search);
				return SUCCESS;
			} else {

				System.out.println("Word_No: " + search);
				return ERROR;
			}
			
			
		} catch (NotBoundException | IOException e) {
			System.out.println("Erro ao se conectar com o servidor RMI");
			//e.printStackTrace();
		} 

		
		return ERROR;
      }
}
