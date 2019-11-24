package model;

import java.util.ArrayList;

/** Guarda as informa��es necess�rias de um site.
 * 
 */
public class Site {
	private String url;
	private String page_title;
	private String text;
	private ArrayList<String> pagesLinks;
	private int numAcess;
	
	/** Define os dados relacionados a um site.
	 * 
	 * @param url 		Url do site.
	 * @param title		Titulo da p�gina do site.
	 * @param text		Uma pequena cita��o do site.
	 */
	public Site(String url, String title, String text ) {
		this.url = url;
		this.page_title = title;
		this.text = text;
		this.numAcess = 0;
		this.pagesLinks = new ArrayList<String>();
	}
	
	/** Pega a url do site.
	 * 
	 * @return	Retorna a URL.
	 */
	public String getUrl() {
		return url;
	}
	
	/** Pega o titulo do site.
	 * 
	 * @return	Retorna o t�tulo da p�gina.
	 */
	public String getPage_title() {
		return page_title;
	}
	
	/** Pega uma pequena cita��o do site.
	 * 
	 * @return	Retorna uma cita��o.
	 */
	public String getText() {
		return text;
	}
	
	/** Pegar o n�mero de acessos que a p�gina tem.
	 * 
	 * @retun Retorna o n�mero de acessos da p�gina.
	 */
	public int getNumAcess() {
		return this.numAcess;
	}
	
	/** Incrementa o n�mero de acessos de uma p�gina.
	 */
	public void addNumAcess() {
		this.numAcess++;
	}
	
	/**Adiciona uma url a lista de p�ginas de liga��o.
	 * 
	 * @param url	Url a qual o site tem liga��o.
	 */
	public void addPagesLinks(String url) {
		this.pagesLinks.add(url);
	}
	
	/** Pega a quantidade de liga��es que este site tem para outros sites.
	 * 
	 * @return N�mero de liga��es.
	 */
	public int getNumPagesLinks() {
		return this.pagesLinks.size();
	}
	
	/** Retorna a lista com as urls que o site tem liga��o.
	 * 
	 * @return	ArrayList de urls de iga��o.
	 */
	public ArrayList<String> getPagesLinks(){
		return this.pagesLinks;
	}
	

	
	

}
