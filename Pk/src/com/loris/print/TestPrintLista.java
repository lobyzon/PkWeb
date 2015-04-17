package com.loris.print;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;

public class TestPrintLista extends AbstractPrint implements Printable {

	public TestPrintLista() {		
	
		//A4: 595 x 842. 21 cm x 29.7 cm		
		PageFormat pf = new PageFormat();
		Paper paper = new Paper();
		paper.setSize(595, 842);
		paper.setImageableArea(10, 10, 595, 842);
		pf.setPaper(paper);
		
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(new MediaPrintableArea(0, 20, 210, 297, MediaPrintableArea.MM));		
		aset.add(OrientationRequested.PORTRAIT);
		aset.add(new Copies(1));
		aset.add(new JobName("Impresion", null));		

		/* Create a print job */
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setPrintable(this, pf);		
		
		//PRINT
		try {			
			pj.setPrintService(PrinterJob.lookupPrintServices()[2]);			
			pj.print(aset);			
		} catch (PrinterException pe) {
			System.err.println(pe);
		}		
	}	

	public int print(Graphics g, PageFormat pf, int pageIndex) {
		final int PULGADA = 11;
		int yPos = 45;
		int height = 0;
		
		if (pageIndex == 0) {
			Font fontS11B = new Font(Font.SANS_SERIF, Font.BOLD, 11);
			Font fontS10 = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
			Font fontS9 = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
			Font fontS9Courier = new Font(COURIER_FONT, Font.PLAIN, 9);
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.translate(pf.getImageableX(), pf.getImageableY());
			
			//FAMILIA			
			g2d.setFont(fontS11B);
			FontMetrics fontMetricsS11 = g2d.getFontMetrics();
			//height += fontMetricsS11.getMaxAscent() + fontMetricsS11.getMaxDescent();
			//g2d.drawString("21" + "  " + "ADHESIVO CEMENTO", 10, yPos);
			//MARCA
			yPos += PULGADA * 1.5;
			g2d.setFont(fontS10);
			FontMetrics fontMetricsS10 = g2d.getFontMetrics();
			height += PULGADA * 1.5;
			height += fontMetricsS10.getMaxDescent();
			//g2d.drawString("380" + "  " + "FORTEX", 10, yPos);
			//ARTICULO
			yPos += PULGADA * 1.5;
			g2d.setFont(fontS9);
			FontMetrics fontMetricsS9 = g2d.getFontMetrics();
			height += PULGADA * 1.5;
			height += fontMetricsS9.getMaxDescent();
			//g2d.drawString("00250", 10, yPos);
			//g2d.drawString("lata de 250 g.", 10 + 35, yPos);
			g2d.setFont(fontS9Courier);
			//g2d.drawString(getAmount(new BigDecimal("4.56"), 8), 10 + 225, yPos);
			System.out.println("Altura: " + height);
			System.out.println("PAGE Height: " + pf.getHeight() + " Page ImageableY: " + pf.getImageableY() + " Page ImageableHeight: " + pf.getImageableHeight());
								
			return Printable.PAGE_EXISTS;
		} else {
			return Printable.NO_SUCH_PAGE;
		}		
	}	
	
	/* Método para imprimir Matriz de impresión
	public int print(Graphics g, PageFormat pf, int pageIndex) {		
		if (pageIndex == 0) {			
			Font fontSansSerif8 = new Font(Font.SANS_SERIF, Font.PLAIN, 8);						
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.translate(pf.getImageableX(), pf.getImageableY());
			
			g2d.setFont(fontSansSerif8);
			
			for(int y = 8; y < 842; y += 8){
				g2d.drawString(String.valueOf(y), 0, y);
				g2d.drawLine(0, y, 595, y);
			}
			
			for(int x = 16; x < 595; x += 16){
				g2d.drawString(String.valueOf(x), x, 8);
				g2d.drawLine(x, 0, x, 842);
			}
						
			return Printable.PAGE_EXISTS;
		} else {
			return Printable.NO_SUCH_PAGE;
		}
	}
	*/
	
	public static void main(String[] args) {
		new TestPrintLista();
	}
}