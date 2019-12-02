package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import model.Site;
import model.Story;
import model.UCBusca;

/** O controler adpta os dados que chegam em pacotes UDP para o sistema, e vice-versa.
 */
public class Controler {
	private UCBusca ucBusca;
	
	/** Inicia o sistema de ucBusca
	 */
	public Controler() {
		ucBusca = new UCBusca();
	}
	
	/** Login.
	 *
	 *@param login 	Vetor com os dados de entrada do login.
	 *@return		Retorna uma string no formato do protocolo udp login.
	 */
	public String login (String [] login) {
		String username = login[1].split(";")[1];
		String password = login[2].split(";")[1];
		
		if(ucBusca.login(username, password)) {
				return "login|registry;true|";
						
		}
		return "login|registry;false|";
		
	}
	
	/** Registro de novos usuários.
	 * 
	 * @param registry	Vetor com os dados de entrada do registro.
	 * @return			Retorna uma string no formato do protocolo udp registro.
	 */
	public String useRegistry (String[] registry) {
		String username = registry[1].split(";")[1];
		String password = registry[2].split(";")[1];
		
		System.out.print(username +":"+ password);
		
		if(ucBusca.useRegitry(username, password)) {			
			return "useRegistry|registry;true|";
		}
		
		return "useRegistry|registry;false|";

		
	}
	
	/** Verifica se um usuário é administrador.
	 * 
	 * @param registry	Vetor com o username do usuário.
	 */
	public String userIsAdmin(String[] request) {
		String username = request[1].split(";")[1];
		
		if(ucBusca.userIsAdmin(username)) {
			return "userIsAdmin|admin;true|";
		}


		return "userIsAdmin|admin;false|";
	}


	
	/**Torna um usuário comum administrador.
	 * 
	 * @param request	Vetor com o usrename do usuário que se tornará administrador.
	 * @return			Retorna uma string no formato do protocolo udp permissão.
	 */
	public String changeUserPermission(String[] request) {
		String username = request[1].split(";")[1];
		
		if(ucBusca.changeUserPermission(username)){
			return "changeUserPermission|change;true|";
		}	

		return "changeUserPermission|change;false|";
	}

	/**Retorna o histórico de acesso de um usuário.
	 * 
	 * @param request	Vetor com o username do usuário que se quer o histórico.
	 * @return			Retorna uma string no formato do protocolo udp.
	 */
	public String getHistoric(String[] request) {
		String username = request[1].split(";")[1];
		
		ArrayList<Story> story = ucBusca.getHistoric(username);
		int size = story.size();
		String result = "getHistoric|size;"+size+"|date;hora;url|";
		
		for(int i =0; i<size; i++) {
			Story s = story.get(i);
			result = result + s.getDate()+";"+s.getHour()+";"+s.getUrl()+"|";
		}
		
		return result;
	}

	/**Indexa uma url ao motor de busca
	 * 
	 * @param request	Vetor com o url que será indexado.
	 * @throws IOException 
	 */
	public void indexURL(String[] request) throws IOException {
		ucBusca.indexURL(request[1].split(";")[1]);
		
	}

	/**Retorna as páginas mais acessadas no sistema.
	 * 
	 * @return			Retorna uma string no formato do protocolo udp.
	 */
	public String getImportantPages() {
		List<Entry<String, Site>> list = ucBusca.getImportantPages();
		int size = list.size();
		
		String result = "getImportantPages|size;"+size+"|url;numero de acesso|";
		
		for(int i=0; i<size; i++) {
			result = result + list.get(i).getValue().getUrl()+";"+list.get(i).getValue().getNumAcess()+"|";
		}

		
		return result;
	}
	
	/**Retorna as palavras mais pesquisadas no sistema.
	 * 
	 * @return			Retorna uma string no formato do protocolo udp.
	 */
	public String getImportantSearch() {
		List<Entry<String, Integer>> list = ucBusca.getImportantSearch();
		int size = list.size();
		
		String result = "getImportantSearch|size;"+size+"|pesquisa;numero de acesso|";
		
		for(int i=0; i<size; i++) {
			result = result + list.get(i).getKey()+";"+list.get(i).getValue()+"|";
		}

		
		return result;
	}
	
	/**Verifica se um usuário tem notificação.
	 * 
	 *@param request	Vetor com o username do usuário.
	 * @return			Retorna uma string no formato do protocolo udp.
	 */
	public String userHasNotification(String[] request) {
		String username = request[1].split(";")[1];
		
		if(ucBusca.userHasNotification(username)) {
			return "userHasNotification|true|";
		}
		
		return "userHasNotification|false|";
	}
	
	/** Retorna a notificação de um usuário.
	 * 
	 * @param 	Vetor com o nome do usuário.
	 * @return	Retorna a notificação.
	 */
	public String getUserNotification(String[] request) {
		String username = request[1].split(";")[1];
		
		return "getUserNotification|notification;"+ucBusca.getUserNotification(username)+"|";
		}
	

	/**Remove a notificação de um usuário.
	 * 	 
	 *@param request Vetor com o username do usuário.
	 * 
	 */
	public void removeUserNotification(String[] request) {
		String username = request[1].split(";")[1];
		ucBusca.removeUserNotification(username);

	}

	public void addHistoric(String[] request) {
		String username = request[1].split(";")[1];
		String date = request[2].split(";")[1];
		String hour = request[3].split(";")[1];
		String url = request[4].split(";")[1];
		
		ucBusca.addHistoric(username, date, hour, url);
	}
	
	public String search(String[] request) {
		System.out.print(request[1]);
		String text = request[1];
		ArrayList<Site> sites = ucBusca.search(text);
		String result;
		
		if(sites != null) {
			 result = "search|size;"+sites.size()+"|";
			
			for(int i=0; i<sites.size(); i++) {
				result = result+"|"+ sites.get(i).getPage_title()+";"+sites.get(i).getUrl()+";"+sites.get(i).getText()+"|";
			}
		}else {
			 result = "search|size;0|";
		}
		
		
		
		
		return result;
		
	}
	

	
	


	




}
