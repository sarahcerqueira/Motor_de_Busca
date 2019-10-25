package model;

import java.util.Date;

/**Unidade utilizada para guarda informa��es importantes de um hist�rico de acesso.
 */
public class Story {
	private String date;
	private String hour;
	private Site site;
	
	/**Configura as informa��es de uma historico.
	 * 
	 * @param date	Data em que o usu�rio acessou o site.
	 * @param hour	Hora em que o usu�rio acessou o site.
	 * @param site	Site que foi acessado.
	 */
	public Story(String date, String hour, Site site) {
		this.date = date;
		this.hour = hour;
		this.site = site;
		
	}

	/**Retorna a data em que o site foi acessado pelo usu�rio.
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
	 * @return Retorna uma instancia do tipo Site, com as informa��es do site visitado.
	 */
	public Site getSite() {
		return site;
	}
	
	
	
	
	
	
	
	

}
