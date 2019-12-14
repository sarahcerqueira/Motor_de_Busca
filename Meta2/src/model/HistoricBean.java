package model;

import java.util.ArrayList;


public class HistoricBean {
	
	private int size;
	private ArrayList<String> historic;
	
	public HistoricBean (ArrayList<String> h) {
		this.historic = h;
		size = h.size();
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}


	public ArrayList<String> getHistoric() {
		return historic;
	}

	public void setHistoric(ArrayList<String> historic) {
		this.historic = historic;
	}

	

}
