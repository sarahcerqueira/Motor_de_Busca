package model;

import java.util.Date;

/**Unidade utilizada para guarda informações importantes de um histórico de acesso.
 */
public class Story {
	private String date;
	private String hour;
	private Site site;
	
	/**Configura as informações de uma historico.
	 * 
	 * @param date	Data em que o usuário acessou o site.
	 * @param hour	Hora em que o usuário acessou o site.
	 * @param site	Site que foi acessado.
	 */
	public Story(String date, String hour, Site site) {
		this.date = date;
		this.hour = hour;
		this.site = site;
		
	}

	/**Retorna a data em que o site foi acessado pelo usuário.
	 * 
	 * @return	Retorna a data de acesso.
	 */
	public String getDate() {
		return date;
	}

	/**Retorna a hora em que o site foi acessado.
	 * 
	 * @return Retorna hora de acesso.
	 */
	public String getHour() {
		return hour;
	}
	
	
	/**Retorna uma instancia do site que foi visitado.
	 * 
	 * @return Retorna uma instancia do tipo Site, com as informações do site visitado.
	 */
	public Site getSite() {
		return site;
	}
	
	
	
	
	
	
	
	

}
