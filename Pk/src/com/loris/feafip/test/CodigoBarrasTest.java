package com.loris.feafip.test;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import ar.com.imprenta_azul.barras.Barras;

public class CodigoBarrasTest {
	
	public static void main(String[] args) {
		Barras barras = new Barras();
		
		DataFlavor flavor = new DataFlavor();
		flavor.setHumanPresentableName("23045244059");
		
		try {
			barras.getTransferData(flavor);
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}