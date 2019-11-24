package model;

import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.Document;

public class Soup {
	
	private Document doc;
	
	public Soup(String url) throws IOException {
		doc = Jsoup.connect(url).get();
	}

}
