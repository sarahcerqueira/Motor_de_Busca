package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/** UCBusca armazena todos os dados do sistema em tempo de compilação. Todas as funcionalidades
 * do sistema estão disponíveis nessa classe.
 */
public class UCBusca {
	private HashMap<String, User> users;
	private HashMap<String, ArrayList<String>> index;
	private HashMap<String, Site> sites; //Armazenamento de sites indexados
	private HashMap<String, Integer> qtdSearch; //String de pesquisa, e a quantidade de vezes que ela foi pesquisada
	
	/** Inicia as estruturas de dados que armazenam os usuários e os sites.
	 */
	public UCBusca() {
		users = new HashMap<String, User>();
		index = new HashMap<String, ArrayList<String>>();
		qtdSearch  = new HashMap<String, Integer>();
	}
	
	//Funcionalidades dos usuários
	/**Verifica se a senha corresponde ao usuário. Caso o usuário não exista ou a senha estiver
	 * incorreta, retorna falso.
	 * 
	 *@param username 		Identificador do usuário.
	 *@param password		Senha do usuário.
	 *@return				Verdadeiro se a senha do usuário estiver correta.
	 */
	public boolean login(String username, String password) {
		
		if(!users.containsKey(username)) {
			return false;	
		} 
		
		User user = users.get(username);
		return user.login(password);
	}
	
	
	/**Registra um novo usuário. Caso o usuário já exista retorna falso.
	 * 
	 *@param username 		Identificador do usuário.
	 *@param password		Senha do usuário.
	 *@return				Verdadeiro se foi possível registrar o usuário.
	 */
	public boolean useRegitry(String username, String password) {
		
		if(users.containsKey(username)) {
			return false;
		}
		
		User user = new User(username, password);
		
		if(this.users.isEmpty()) {
			user.setAdmin(true);
		}
		
		this.users.put(username, user);
		return true;
	}
	
	/** Torna um usuário administrador.
	 * 
	 * @param username 		Identificador do usuário.
	 * @return				Retorna falso se o usuário não existir.
	 */
	public boolean changeUserPermission(String username) {
		
		if(!users.containsKey(username)) {
			return false;	
		} 
		
	
		this.addNotification(username, "Você agora é um administrador");		
		User user = users.get(username);
		user.setAdmin(true);
		return true;
	}
	
	/** Pega o histórico de acesso de um usuário. Caso o usuário não exista retorna nulo.
	 * 
	 * @param username	Identificado do usuário, o qual se quer o histórico de acesso.
	 * @return			Retorna uma lista com o histórico de acesso do usuário.
	 */
	public ArrayList<Story> getHistoric (String username){
		
		if(!users.containsKey(username)) {
			return null;	
		}
		
		return users.get(username).getHistoric();

	}
	
	/** Adiciona um histórico de notificação para um usuário.
	 * 
	 * @param username		Identificador do usuário.
	 * @param date			Data de acesso
	 * @param hour			Hora do acesso
	 * @param site			O site que foi acessado.
	 */
	public void addHistoric(String username,String date, String hour, Site site ) {
		
		if(users.containsKey(username)) {
			User user = users.get(username);
			user.addStory(date, hour, site);
		}
		
	}
	
	/** Adiciona notificação para um usuário.
	 * 
	 * @param username		Identificador do usuário ao qual se quer criar uma notificação.
	 * @param notification 	Mensagem de notificação.
	 */
	public void addNotification(String username, String notification){
		
		if(users.containsKey(username)) {
			User user = users.get(username);
			user.addNotification(notification);;
		}
	}
	
	/** Verifica se um usuário tem notificação.
	 * 
	 * @param username 	Identificador do usuário.
	 * @return			Retorna verdadeiro se o usuário tem notificações.
	 */
	public boolean userHasNotification(String username) {
		
		if(users.containsKey(username)) {
			User user = users.get(username);
			return user.hasNotification();
		}
		
		return false;	
	}
	
	/** Pega a notificação de um usuário.
	 * 
	 * @param username 	Identificador do usuário.
	 * @return			Retorna a notificação do usuário, ou nulo caso o usuário não exista.
	 */
	public String getUserNotification(String username) {
		
		if(users.containsKey(username)) {
			User user = users.get(username);
			return user.getNotification();
		}
		
		return null;	
	}
	
	
	/**Remove a notificação de um usuário.
	 * 
	 * @param username 	Identificador do usuário.
	 * @return			Retorna a notificação do usuário, ou nulo caso o usuário não exista.
	 */
	public void removeUserNotification(String username) {
		
		if(users.containsKey(username)) {
			User user = users.get(username);
			user.removeNotification();
		}
	}
	
	/**Verifica se um usuário é administrador.
	 * 
	 * @param username 	Identificador do usuário.
	 * @return			Retornar verdadeiro se o usuário for administrador.
	 */
	public boolean userIsAdmin(String username) {
		
		if(users.containsKey(username)) {
			User user = users.get(username);
			return user.isAdmin();
		}
		
		return false;
	}
	
	//Funcionalidade de administrador

	/** Indexa urls ao software.
	 * 
	 * @param url 			URL que será indexado.
	 * @throws IOException 
	 */
	public void indexURL(String url) throws IOException{
		this.indexURL(url, null, 0);
		
	}
	
	
	/** Indexa urls ao software
	 * 
	 * @param url 			URL que será indexado.
	 * @param urlPrevious	URL que tem link para o site atual.
	 * @param level			Refere-se à profundidade na qual está a recussão. 
	 * @throws IOException 
	 */
	private void indexURL(String url, String urlPrevious, int level) throws IOException {
		Site site; Document doc;
		
		//Se o site já estiver indexado, adiciona ao site os sites que fazem ligação para ele.
		if(this.sites.containsKey(url) && level != 0) {
			site = sites.get(url);
			site.addPagesLinks(urlPrevious);
			return;
		}
		
		//Se a indexação estiver em nível 3, para de indexar.
		if(level > 3) {
			return;
		}
		
		doc = Jsoup.connect(url).get();
		site = new Site(url, doc.title(), doc.text());
		this.invertedIndex(doc.text(), url);
		
		//Em nível 0 não há urlPrevious
		if(level > 0)
			site.addPagesLinks(urlPrevious);
		
		Elements links = doc.select("a[href]");
		
		for (Element link : links) {
			
            if (link.attr("href").startsWith("#") || !link.attr("href").startsWith("http")) {
                continue;
            }
            indexURL(link.attr("href"), url, level++);

        }
	
	}
	
	/**Gera o index invertido para cada palavra do texto nos sites.
	 * 
	 * @param text		Texto do site.
	 * @param url		Url a qual pertence o texto.
	 */
	private void invertedIndex(String text, String url) {
		String[] words = text.split(" |.|:|?|\n|\t|;|\r"); //Quebra o texto do site em palavras
		ArrayList<String> array;
		
		//Para cada palavra que há no texto
		for(String word: words) {
			
			//Se a palavra já está no index
			if(index.containsKey(word)) {
				array = index.get(word);
				
				if(!array.contains(url))
					array.add(url);
				
			//Senão adiciona
			} else {
				array = new ArrayList<String>();
				array.add(url);
				index.put(word, array);
			}
		}
	}
	

	
	
	/**Retorna as 10 páginas mais acessadas.
	 */
	public List<Entry<String, Integer>> getImportantPages(){
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(this.qtdAcess.entrySet());
		Collections.sort(list, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				Entry<String, Integer> mapa1 =  (Entry<String, Integer>) o1;
				Entry<String, Integer> mapa2 =  (Entry<String, Integer>) o2;
				
				return mapa1.getValue().compareTo(mapa2.getValue());
			}});
		
		if(list.size()> 10) {
			List<Entry<String, Integer>> result = new LinkedList<Entry<String, Integer>>();
			
			for(int i =0; i<10; i++) {
				result.add(list.get(i));
			}
			
			list = result;
		}
		
		return list;
	}
	
	/**Retorna as 10 pesquisas mais importantes.
	 */
	public List<Entry<String, Integer>> getImportantSearch(){
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(this.qtdSearch.entrySet());
		Collections.sort(list, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				Entry<String, Integer> mapa1 =  (Entry<String, Integer>) o1;
				Entry<String, Integer> mapa2 =  (Entry<String, Integer>) o2;
				
				return mapa1.getValue().compareTo(mapa2.getValue());
			}});
		
		if(list.size()> 10) {
			List<Entry<String, Integer>> result = new LinkedList<Entry<String, Integer>>();
			
			for(int i =0; i<10; i++) {
				result.add(list.get(i));
			}
			
			list = result;
		}
		
		return list;
       		
	}
	
	/** Retornas os servidores Multicast ativos.
	 */
	public void getServerMulticastActive(){}
	
	
	//Funcionalidades do sistema
	public void search (String text){}
	
	
	//Manutenção do sistema
	/**Sinconiza a lista de usuário. Quando um novo usuário é cadastro, essa sincroização envia para
	 * os outros servidores Multicast essa atualização, para que todos tenham os mesmos usuários 
	 * cadastrados.
	 * 
	 */
	public void userSync() {}
	
	/** Sincroniza as urls cadastradas com os outros servidores multicast.
	 */
	public void indexSync() {}
	
	/** Sincroniza a quantidade de acesso por site com os outros servidores multicast.
	 */
	public void qtdAcessSync() {}
	
	/** Sincroniza a quantidade de vezes que uma palavra foi pesquisada.
	 */
	public void qtdSearchSync() {}
	

}
