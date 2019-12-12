package model;

import java.util.ArrayList;

/** Define um usuário do sistema. Cada usuário tem uma username, senha, uma váriavel que indica 
 * se ele é um administrador ou não, uma lista de notificação e um histórico de pesquisa.
 * O histórico de notificações é para informa ao usuário das suas alterações de permissão no 
 * sistema. O histórico de notificações funciona como uma fila.
 * Já o histórico de pesquisa quarta todos os sites que fora acessados pelo usuário.
 * 
 */
public class User {
	private String username;
	private String password;
	private boolean admin;
	private ArrayList<String> notification;
	private ArrayList<Story> historic;
	
	/**Configura informações importantes relacionadas a um usuário, e
	 * inicia os arrays de histórico e notificações.
	 * 
	 * @param username 		Username do usuário.
	 * @param password		Senha do usuário.
	 * @param admin			Define se o usuário é um administrador ou não.
	 */
	public User (String username, String password) {
		this.username = username;
		this.password = password;
		this.admin = false;
		this.notification = new ArrayList<String>();
		this.historic = new ArrayList<Story>();
	}
	
	
	/** Confere se a password passada corresponde com a password do usuário.
	 * 
	 * @param password	Senha a ser conferida.
	 * @return 			Retorna False se a senha não corresponder a senha do usuário.
	 */
	public boolean login(String password){
		if(this.password.equals(password)) {
			return true;
		}
		
		return false;
		
	}
	
	/** Verifica se um usuário é administrador ou não.
	 * 
	 * @return 		Retorna verdadeiro se o usuário for administrador.
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**Muda a permissão de usuário.
	 * 
	 * @param admin 	Boolean que confirma ou não que um usuário é administrador.
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/** Retorna o username do usuário.
	 * 
	 * @return		Retorna uma String com o username do usuário.
	 */
	public String getUsername() {
		return username;
	}
	
	/** Pega o histórico do usuário.
	 * 
	 * @return		Retorna um array list de Story, que contém o histórico de acesso de m usuário.
	 */
	public ArrayList<Story> getHistoric(){
		return this.historic;
	}

	/**Adiciona um histórico de acesso.
	 * 
	 * @param time		Hora e data em que  site foi acessado
	 * @param url		Url do site visitado.
	 */
	public void addStory(String time, String url){
		this.checkIfUrlExit(url);
		this.historic.add(new Story(time, url));
		
	}
	
	/**Este método serve para verificar se um site já está no histórico. Se o site for encontrado
	 * no histórico, o site é removido. Isso ajuda que um site não tenha dois históricos de acesso
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
	
	/** Verifica se há notificações para um determinado usuário.
	 * 
	 * @return 		Retorna verdadeiro se hover notificações.
	 */
	public boolean hasNotification() {
		
		if(this.notification.isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	/**Retorna a notificação mais antiga cadatrada. Caso o array de notificações estiver vazio,
	 * retorna nulo.
	 * 
	 * @return		Retorna a String de notificação se hover notificação, ou nulo.
	 */
	public String getNotification() {
		if(!this.notification.isEmpty()) {
			return this.notification.get(0);
		}
		
		return null;
	}
	
	/**Remove as notificações mais antigas. Se não houver notificações para serem excluídas 
	 * nada é feito.
	 */
	public void removeNotification() {
		if(!this.notification.isEmpty()) {
			this.notification.remove(0);
		}
	}
	
	/** Adiciona uma noticiação para um usuário.
	 * 
	 * @param notification 	Mensagem de notificação.
	 */
	public void addNotification(String notification) {
		this.notification.add(notification);
	}
	
	

}
