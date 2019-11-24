package model;

import java.util.ArrayList;

/** Guarda as informações necessárias de um site.
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
	 * @param title		Titulo da página do site.
	 * @param text		Uma pequena citação do site.
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
	 * @return	Retorna o título da página.
	 */
	public String getPage_title() {
		return page_title;
	}
	
	/** Pega uma pequena citação do site.
	 * 
	 * @return	Retorna uma citação.
	 */
	public String getText() {
		return text;
	}
	
	/** Pegar o número de acessos que a página tem.
	 * 
	 * @retun Retorna o número de acessos da página.
	 */
	public int getNumAcess() {
		return this.numAcess;
	}
	
	/** Incrementa o número de acessos de uma página.
	 */
	public void addNumAcess() {
		this.numAcess++;
	}
	
	/**Adiciona uma url a lista de páginas de ligação.
	 * 
	 * @param url	Url a qual o site tem ligação.
	 */
	public void addPagesLinks(String url) {
		this.pagesLinks.add(url);
	}
	
	/** Pega a quantidade de ligações que este site tem para outros sites.
	 * 
	 * @return Número de ligações.
	 */
	public int getNumPagesLinks() {
		return this.pagesLinks.size();
	}
	
	/** Retorna a lista com as urls que o site tem ligação.
	 * 
	 * @return	ArrayList de urls de igação.
	 */
	public ArrayList<String> getPagesLinks(){
		return this.pagesLinks;
	}
	

	
	

}
