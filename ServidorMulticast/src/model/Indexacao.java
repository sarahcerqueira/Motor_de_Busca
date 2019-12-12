package model;

import java.io.IOException;

public class Indexacao implements Runnable {
	
	private UCBusca uc;
	private String url;
	
	public Indexacao (UCBusca uc, String url) {
		this.uc = uc;
		this.url = url;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			try {
				uc.indexURL(url, null, 0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
		
		
	}

}
