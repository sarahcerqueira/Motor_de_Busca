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

/** UCBusca armazena todos os dados do sistema em tempo de compila��o. Todas as funcionalidades
 * do sistema est�o dispon�veis nessa classe.
 */
public class UCBusca {
	private HashMap<String, User> users;
	private HashMap<String, ArrayList<String>> index;
	private HashMap<String, Site> sites; //Armazenamento de sites indexados
	private HashMap<String, Integer> qtdSearch; //String de pesquisa, e a quantidade de vezes que ela foi pesquisada
	
	/** Inicia as estruturas de dados que armazenam os usu�rios e os sites.
	 */
	public UCBusca() {
		users = new HashMap<String, User>();
		index = new HashMap<String, ArrayList<String>>();
		qtdSearch  = new HashMap<String, Integer>();
		sites = new HashMap<String, Site>();
	}
	
	//Funcionalidades dos usu�rios
	/**Verifica se a senha corresponde ao usu�rio. Caso o usu�rio n�o exista ou a senha estiver
	 * incorreta, retorna falso.
	 * 
	 *@param username 		Identificador do usu�rio.
	 *@param password		Senha do usu�rio.
	 *@return				Verdadeiro se a senha do usu�rio estiver correta.
	 */
	public boolean login(String username, String password) {
		
		if(!users.containsKey(username)) {
			return false;	
		} 
		
		User user = users.get(username);
		return user.login(password);
	}
	
	
	/**Registra um novo usu�rio. Caso o usu�rio j� exista retorna falso.
	 * 
	 *@param username 		Identificador do usu�rio.
	 *@param password		Senha do usu�rio.
	 *@return				Verdadeiro se foi poss�vel registrar o usu�rio.
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
	
	/** Torna um usu�rio administrador.
	 * 
	 * @param username 		Identificador do usu�rio.
	 * @return				Retorna falso se o usu�rio n�o existir.
	 */
	public boolean changeUserPermission(String username) {
		
		if(!users.containsKey(username)) {
			return false;	
		} 
		
	
		this.addNotification(username, "Voc� agora � um administrador");		
		User user = users.get(username);
		user.setAdmin(true);
		return true;
	}
	
	/** Pega o hist�rico de acesso de um usu�rio. Caso o usu�rio n�o exista retorna nulo.
	 * 
	 * @param username	Identificado do usu�rio, o qual se quer o hist�rico de acesso.
	 * @return			Retorna uma lista com o hist�rico de acesso do usu�rio.
	 */
	public ArrayList<Story> getHistoric (String username){
		
		if(!users.containsKey(username)) {
			return null;	
		}
		
		return users.get(username).getHistoric();

	}
	
	/** Adiciona um hist�rico de notifica��o para um usu�rio.
	 * 
	 * @param username		Identificador do usu�rio.
	 * @param date			Data de acesso
	 * @param hour			Hora do acesso
	 * @param url			O site que foi acessado.
	 */
	public void addHistoric(String username,String date, String hour, String url ) {
		
		if(users.containsKey(username)) {
			User user = users.get(username);
			user.addStory(date, hour, url);
		}
		
	}
	
	/** Adiciona notifica��o para um usu�rio.
	 * 
	 * @param username		Identificador do usu�rio ao qual se quer criar uma notifica��o.
	 * @param notification 	Mensagem de notifica��o.
	 */
	public void addNotification(String username, String notification){
		
		if(users.containsKey(username)) {
			User user = users.get(username);
			user.addNotification(notification);;
		}
	}
	
	/** Verifica se um usu�rio tem notifica��o.
	 * 
	 * @param username 	Identificador do usu�rio.
	 * @return			Retorna verdadeiro se o usu�rio tem notifica��es.
	 */
	public boolean userHasNotification(String username) {
		
		if(users.containsKey(username)) {
			User user = users.get(username);
			return user.hasNotification();
		}
		
		return false;	
	}
	
	/** Pega a notifica��o de um usu�rio.
	 * 
	 * @param username 	Identificador do usu�rio.
	 * @return			Retorna a notifica��o do usu�rio, ou nulo caso o usu�rio n�o exista.
	 */
	public String getUserNotification(String username) {
		
		if(users.containsKey(username)) {
			User user = users.get(username);
			return user.getNotification();
		}
		
		return null;	
	}
	
	
	/**Remove a notifica��o de um usu�rio.
	 * 
	 * @param username 	Identificador do usu�rio.
	 * @return			Retorna a notifica��o do usu�rio, ou nulo caso o usu�rio n�o exista.
	 */
	public void removeUserNotification(String username) {
		
		if(users.containsKey(username)) {
			User user = users.get(username);
			user.removeNotification();
		}
	}
	
	/**Verifica se um usu�rio � administrador.
	 * 
	 * @param username 	Identificador do usu�rio.
	 * @return			Retornar verdadeiro se o usu�rio for administrador.
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
	 * @param url 			URL que ser� indexado.
	 * @throws IOException 
	 */
	public void indexURL(String url) throws IOException{
		this.indexURL(url, null, 0);
		
	}
	
	
	/** Indexa urls ao software
	 * 
	 * @param url 			URL que ser� indexado.
	 * @param urlPrevious	URL que tem link para o site atual.
	 * @param level			Refere-se � profundidade na qual est� a recuss�o. 
	 * @throws IOException 
	 */
	private void indexURL(String url, String urlPrevious, int level) throws IOException {
		Site site; Document doc;
		
		//Se o site j� estiver indexado, adiciona ao site os sites que fazem liga��o para ele.
		if(this.sites.containsKey(url) && level != 0) {
			site = sites.get(url);
			site.addPagesLinks(urlPrevious);
			return;
		}
		
		//Se a indexa��o estiver em n�vel 3, para de indexar.
		if(level > 3) {
			return;
		}
		
		doc = Jsoup.connect(url).get();
		site = new Site(url, doc.title(), doc.text());
		this.invertedIndex(doc.text(), url);
		
		//Em n�vel 0 n�o h� urlPrevious
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
		String[] words = text.split("[ |.|:|?|\n|\t|;|\r|�|�|(|)|{|}|\\\\[\\\\]|<|>|]+"); //Quebra o texto do site em palavras
		ArrayList<String> array;
		
		//Para cada palavra que h� no texto
		for(String word: words) {
			
			if ("".equals(word)) {
                continue;
            }
			
			//Se a palavra j� est� no index
			if(index.containsKey(word)) {
				array = index.get(word);
				
				if(!array.contains(url))
					array.add(url);
				
			//Sen�o adiciona
			} else {
				array = new ArrayList<String>();
				array.add(url);
				index.put(word, array);
			}
		}
	}
	

	
	
	/**Retorna as 10 p�ginas mais acessadas.
	 */
	public List<Entry<String, Site>> getImportantPages(){
		List<Entry<String, Site>> list = new LinkedList<Entry<String, Site>>(this.sites.entrySet());
		
		//Ordena a lista pelo n�mero de acesso dos sites
		Collections.sort(list, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				Entry<String, Site> mapa1 =  (Entry<String, Site>) o1;
				Entry<String, Site> mapa2 =  (Entry<String, Site>) o2;
				
				return new Integer(mapa1.getValue().getNumAcess()).compareTo(new Integer (mapa2.getValue().getNumAcess()));
			}});
		
		//Pega somente os 10 primeiros sites
		if(list.size()> 10) {
			List<Entry<String, Site>> result = new LinkedList<Entry<String, Site>>();
			
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
	

	//Funcionalidades do sistema
	
	/**Busca sites a partir de uma string.
	 * 
	 * @param text		Texto de Busca
	 * @return			Retorna uma lista com os sites em ordem de import�ncia.
	 * 
	 * 
	 */
	public ArrayList<Site> search (String text){
		
		this.addQtdSearch(text);
		
		if(index.isEmpty())
			return null;
		
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<ArrayList<String>> conjSites = new ArrayList<ArrayList<String>>();
		String[] words = text.split("[ |.|:|?|\n|\t|;|\r|�|�|(|)|{|}|\\\\[\\\\]|<|>|]+");
		ArrayList<String> copy;
		
		//Pega todos os arrays de sites relacionados a todas as palavras da pesquisa
		for(String word: words) {
			if(index.containsKey(word)) {
				copy = new ArrayList<String>();
				copy.addAll(index.get(word));
				conjSites.add(copy);}
		}
		
		//Se est� vazio n�o existe resultados para essa pesquisa
		if(conjSites.isEmpty())
					return null;
		
		
		//Se � maior que 1 � necessario pegar os sites incomuns
		else if(conjSites.size()> 1) {
			copy = conjSites.get(0);
			int size = conjSites.size();
			int sizeCopy = copy.size();
			boolean exist = true;
		
			
			for(int i=0; i<sizeCopy; i++) {	//Para cada site da primeira lista
				String url = copy.get(i);	//Pega o url do site
				
				for(int j=0; j<size; j++) {	//Verifica se cont�m nos outros arrays
					if(!conjSites.get(j).contains(url)) {
						exist = false;
					}
				}
				//Ao termina o segundo loop  e exist ainda for verdadeiro significa que o url faz parte da intersec��o
				if(exist) {
					result.add(url);
				}else 
					exist = true;
			}			
		
		}
		
		return this.getSites(result);
		
	}	
		
	
	/** A partir e uma lista de urls, retorna todas as inst�ncia de sites.
	 * 
	 * @param urls 		Lista de urls.
	 * @return			Lista de Sites ordenados por import�ncia.
	 */
	private ArrayList<Site> getSites(ArrayList<String> urls){
		ArrayList<Site> site = new ArrayList<Site>();
		int size = urls.size();
		
		for(int i=0; i<size; i++) {
			Site s = this.sites.get(urls.get(i));
			site.add(s);
		}
		
		Collections.sort(site);
		
		return site;
	}
	
	/**Incrementa a quantidade de vezes que aquele texto foi pesquisado.
	 * 
	 * @param text	Texto que foi pesquisado.
	 */
	private void addQtdSearch(String text) {
		
		if(this.qtdSearch.containsKey(text)) {
			Integer i = this.qtdSearch.get(text);
			this.qtdSearch.replace(text, i++);
			
		}else{
			this.qtdSearch.put(text, 1);
		}
		
	}
	


}
