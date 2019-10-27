package model;

/** Guarda as informações necessárias de um site.
 * 
 */
public class Site {
	private String url;
	private String page_title;
	private String quote;
	
	/** Define os dados relacionados a um site.
	 * 
	 * @param url 		Url do site.
	 * @param title		Titulo da página do site.
	 * @param quote		Uma pequena citação do site.
	 */
	private Site(String url, String title, String quote ) {
		this.url = url;
		this.page_title = title;
		this.quote = quote;
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
	public String getQuote() {
		return quote;
	}
	
	

}
