package model;

import java.util.ArrayList;


public class Bean {
	
	private int size;
	private ArrayList<String> text;
	
	public Bean (ArrayList<String> h) {
		this.text = h;
		System.out.println(text);
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

	public ArrayList<String> getText() {
		return text;
	}

	public void setText(ArrayList<String> text) {
		this.text = text;
	}




	

}
