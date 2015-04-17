package com.loris.bean;

public class Printer {
	private int printerServiceIndex;

	public Printer(){}
	
	public Printer(int printerServiceIndex){
		this.printerServiceIndex = printerServiceIndex;
	}
	
	public int getPrinterServiceIndex() {
		return printerServiceIndex;
	}

	public void setPrinterServiceIndex(int printerServiceIndex) {
		this.printerServiceIndex = printerServiceIndex;
	}
}