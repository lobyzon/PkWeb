package com.loris.print;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;

import com.loris.domain.Factura;

public class TestPrintFacturaN extends AbstractPrint implements Printable {
	private boolean laser;
	
	public TestPrintFacturaN() {
	
		//A4: 595 x 842. 21 cm x 29.7 cm		
		PageFormat pf = new PageFormat();
		Paper paper = new Paper();
		paper.setSize(595, 842);
		paper.setImageableArea(0, 0, 595, 842);
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
			PrintService printService = PrinterJob.lookupPrintServices()[2];
			this.laser = getIsLaser(printService.getName()); 
			pj.setPrintService(printService);
			pj.print(aset);			
		} catch (PrinterException pe) {
			System.err.println(pe);
		}		
	}	

	private boolean getIsLaser(String laserPrinterName) {
		if(laserPrinterName.contains("Samsung"))
			return true;
		return false;
	}

	public int print(Graphics g, PageFormat pf, int pageIndex) {
		int y = 173;
		final int PULGADA = 14;
		
		if (pageIndex == 0) {
			Font fontLetrasMayus = new Font(Font.SANS_SERIF, Font.PLAIN, 7);
			Font fontLetras = new Font(Font.SANS_SERIF, Font.PLAIN, 8);
			Font fontCourier8 = new Font(COURIER_FONT, Font.PLAIN, 8);
			Font fontCourier9 = new Font(COURIER_FONT, Font.PLAIN, 9);
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.translate(pf.getImageableX(), pf.getImageableY());
		
			//Print Item Titles
			g2d.setFont(fontLetras);
			g2d.drawString("CODIGO", getLaserAdjust(this.laser, 30), 167);
			g2d.drawString("DESCRIPCION", getLaserAdjust(this.laser, 126), 167);
			g2d.drawString("CANT.", getLaserAdjust(this.laser, 408), 167);
			g2d.drawString("DESC %", getLaserAdjust(this.laser, 370) + PULGADA * 5, 167);
			g2d.drawString("PRECIO", getLaserAdjust(this.laser, 373) + PULGADA * 8, 167);
			g2d.drawString("IMPORTE", getLaserAdjust(this.laser, 355) + PULGADA * 12, 167);
			
			for(int i = 1; i <= 30; i++){
				y += 14;
							
				if(i == 1 || i == 30){
					g2d.setFont(fontCourier8);
					g2d.drawString(	"123" + " " + "321" + " " + "123456 ", getLaserAdjust(this.laser, 20), y);
					
					g2d.setFont(fontLetrasMayus);
					String cadenaLetras = "FAMILIA DE UNOS CARACTERE" + "  " +
					"Descripción de unos 30 caracte" + "  ";
					
					cadenaLetras += "MARCA DE 16 CAR.";
					g2d.drawString(cadenaLetras, getLaserAdjust(this.laser, 92), y);
					
					g2d.setFont(fontCourier8);
					g2d.drawString(getNormalizedWidthCode("1000", 6), getLaserAdjust(this.laser, 402), y);
					g2d.drawString(getAmount(new BigDecimal("35.00"), 4), getLaserAdjust(this.laser, 373) + PULGADA * 5, y);
					g2d.drawString(getAmount(new BigDecimal("1000.00"), 8), getLaserAdjust(this.laser, 363) + PULGADA * 8, y);
					g2d.drawString(getAmount(new BigDecimal("3000.00"), 8), getLaserAdjust(this.laser, 352) + PULGADA * 12, y);
				}
			}
			
			//PRINT Totales
			g2d.setFont(fontCourier9);
			g2d.drawString("Subtotal", 460, 700);
			g2d.drawString(getAmount(new BigDecimal("200.15"), 8), 510, 700);						
			g2d.drawString("Dto.", 460, 712);
			g2d.drawString(getAmount(new BigDecimal("50.12"), 8), 510, 712);
			g2d.drawString("Subtotal", 460, 724);
			g2d.drawString(getAmount(new BigDecimal("150"), 8), 510, 724);
			
			//Comentarios
			g2d.drawRect(getLaserAdjust(laser, 20), 680, 340, 70);
			g2d.setFont(fontLetras);			
			g2d.drawString("COMENTARIOS:", getLaserAdjust(laser, 25), 690);
			Factura factura = new Factura();
			factura.setComentarios("Cheque:   14-05-2011 $ 2000");
			g2d.setFont(fontCourier8);
			printComentarios(g2d, factura, getLaserAdjust(this.laser, 700), 25);
			
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
		new TestPrintFacturaN();
	}
}