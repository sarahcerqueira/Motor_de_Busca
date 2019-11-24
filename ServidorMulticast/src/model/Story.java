package model;

import java.util.Date;

/**Unidade utilizada para guarda informa��es importantes de um hist�rico de acesso.
 */
public class Story {
	private String date;
	private String hour;
	private String url;
	
	/**Configura as informa��es de uma historico.
	 * 
	 * @param date	Data em que o usu�rio acessou o site.
	 * @param hour	Hora em que o usu�rio acessou o site.
	 * @param url	Url que foi acessado.
	 */
	public Story(String date, String hour, String url) {
		this.date = date;
		this.hour = hour;
		this.url = url;
		
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
	
	
	/**Retorna a url do site que foi visitado.
	 * 
	 * @return Url do site visitado.
	 */
	public String getUrl() {
		return url;
	}
	
	
	
	
	
	
	
	

}
