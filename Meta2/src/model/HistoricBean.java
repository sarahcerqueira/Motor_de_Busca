package model;

import java.util.ArrayList;


public class HistoricBean {
	
	private int size;
	private ArrayList<String> historic;
	
	public HistoricBean (ArrayList<String> h) {
		this.historic = h;
		System.out.println(historic);
		//System.out.println("Hey");
		size = h.size();
	}
	
	public int getSize() {
		System.out.println(size);
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
