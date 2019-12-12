package model;

import java.util.Date;

/**Unidade utilizada para guarda informa��es importantes de um hist�rico de acesso.
 */
public class Story {
	
	private String time;
	private String url;
	
	/**Configura as informa��es de uma historico.
	 * 
	 * @param date	Data em que o usu�rio acessou o site.
	 * @param hour	Hora em que o usu�rio acessou o site.
	 * @param url	Url que foi acessado.
	 */
	public Story(String time, String url) {
		this.time = time;
		this.url = url;
		
	}



	/**Retorna a hora em que o site foi acessado.
	 * 
	 * @return Retorna hora de acesso.
	 */
	public String getTime() {
		return time;
	}
	
	
	/**Retorna a url do site que foi visitado.
	 * 
	 * @return Url do site visitado.
	 */
	public String getUrl() {
		return url;
	}
	
	
	
	
	
	
	
	

}
