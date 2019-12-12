package model;

import java.util.ArrayList;

/** Define um usu�rio do sistema. Cada usu�rio tem uma username, senha, uma v�riavel que indica 
 * se ele � um administrador ou n�o, uma lista de notifica��o e um hist�rico de pesquisa.
 * O hist�rico de notifica��es � para informa ao usu�rio das suas altera��es de permiss�o no 
 * sistema. O hist�rico de notifica��es funciona como uma fila.
 * J� o hist�rico de pesquisa quarta todos os sites que fora acessados pelo usu�rio.
 * 
 */
public class User {
	private String username;
	private String password;
	private boolean admin;
	private ArrayList<String> notification;
	private ArrayList<Story> historic;
	
	/**Configura informa��es importantes relacionadas a um usu�rio, e
	 * inicia os arrays de hist�rico e notifica��es.
	 * 
	 * @param username 		Username do usu�rio.
	 * @param password		Senha do usu�rio.
	 * @param admin			Define se o usu�rio � um administrador ou n�o.
	 */
	public User (String username, String password) {
		this.username = username;
		this.password = password;
		this.admin = false;
		this.notification = new ArrayList<String>();
		this.historic = new ArrayList<Story>();
	}
	
	
	/** Confere se a password passada corresponde com a password do usu�rio.
	 * 
	 * @param password	Senha a ser conferida.
	 * @return 			Retorna False se a senha n�o corresponder a senha do usu�rio.
	 */
	public boolean login(String password){
		if(this.password.equals(password)) {
			return true;
		}
		
		return false;
		
	}
	
	/** Verifica se um usu�rio � administrador ou n�o.
	 * 
	 * @return 		Retorna verdadeiro se o usu�rio for administrador.
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**Muda a permiss�o de usu�rio.
	 * 
	 * @param admin 	Boolean que confirma ou n�o que um usu�rio � administrador.
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/** Retorna o username do usu�rio.
	 * 
	 * @return		Retorna uma String com o username do usu�rio.
	 */
	public String getUsername() {
		return username;
	}
	
	/** Pega o hist�rico do usu�rio.
	 * 
	 * @return		Retorna um array list de Story, que cont�m o hist�rico de acesso de m usu�rio.
	 */
	public ArrayList<Story> getHistoric(){
		return this.historic;
	}

	/**Adiciona um hist�rico de acesso.
	 * 
	 * @param time		Hora e data em que  site foi acessado
	 * @param url		Url do site visitado.
	 */
	public void addStory(String time, String url){
		this.checkIfUrlExit(url);
		this.historic.add(new Story(time, url));
		
	}
	
	/**Este m�todo serve para verificar se um site j� est� no hist�rico. Se o site for encontrado
	 * no hist�rico, o site � removido. Isso ajuda que um site n�o tenha dois hist�ricos de acesso
	 * diferente, economizando recursos de armazenamento.
	 * 
	 * @param site 	Instancia do site.
	 */
	private void checkIfUrlExit(String url) {
		int tam = this.historic.size();
		
		for(int i = 0; i<tam; i++) {
			
			if(this.historic.get(i).getUrl().equals(url)){
				this.historic.remove(i);
				break;
			}
		}
		
	}
	
	/** Verifica se h� notifica��es para um determinado usu�rio.
	 * 
	 * @return 		Retorna verdadeiro se hover notifica��es.
	 */
	public boolean hasNotification() {
		
		if(this.notification.isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	/**Retorna a notifica��o mais antiga cadatrada. Caso o array de notifica��es estiver vazio,
	 * retorna nulo.
	 * 
	 * @return		Retorna a String de notifica��o se hover notifica��o, ou nulo.
	 */
	public String getNotification() {
		if(!this.notification.isEmpty()) {
			return this.notification.get(0);
		}
		
		return null;
	}
	
	/**Remove as notifica��es mais antigas. Se n�o houver notifica��es para serem exclu�das 
	 * nada � feito.
	 */
	public void removeNotification() {
		if(!this.notification.isEmpty()) {
			this.notification.remove(0);
		}
	}
	
	/** Adiciona uma noticia��o para um usu�rio.
	 * 
	 * @param notification 	Mensagem de notifica��o.
	 */
	public void addNotification(String notification) {
		this.notification.add(notification);
	}
	
	

}
