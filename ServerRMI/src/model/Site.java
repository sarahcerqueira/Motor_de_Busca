package model;

/** Guarda as informa��es necess�rias de um site.
 * 
 */
public class Site {
	private String url;
	private String page_title;
	private String quote;
	
	/** Define os dados relacionados a um site.
	 * 
	 * @param url 		Url do site.
	 * @param title		Titulo da p�gina do site.
	 * @param quote		Uma pequena cita��o do site.
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
	 * @return	Retorna o t�tulo da p�gina.
	 */
	public String getPage_title() {
		return page_title;
	}
	
	/** Pega uma pequena cita��o do site.
	 * 
	 * @return	Retorna uma cita��o.
	 */
	public String getQuote() {
		return quote;
	}
	
	

}
