package com.loris.print;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Date;
import java.util.List;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;

import com.loris.domain.Articulo;
import com.loris.utils.DateUtils;

public class PrintLista extends AbstractPrint implements Printable {

	private List<Articulo> articulos;	

	public PrintLista(List<Articulo> articulos) {		

		this.articulos = articulos;

		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(MediaSizeName.ISO_A4);		
		aset.add(OrientationRequested.PORTRAIT);		
		aset.add(new Copies(1));
		aset.add(new JobName("Impresión Lista", null));		

		/* Create a print job */
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setPrintable(this);
		/* locate a print service that can handle the request */
		PrintService[] services = PrinterJob.lookupPrintServices();

		if (services.length > 0) {
			System.out.println("selected printer " + services[0].getName());
			try {
				pj.setPrintService(services[0]);
				pj.print(aset);
			} catch (PrinterException pe) {
				System.err.println(pe);
			}
		}
	}	

	public int print(Graphics g, PageFormat pf, int pageIndex) {
		final int PULGADA = 10;
		if (pageIndex == 0) {
			Font fontS10 = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
			Font fontS10B = new Font(Font.SANS_SERIF, Font.BOLD, 10);
			Font fontS8 = new Font(Font.SANS_SERIF, Font.PLAIN, 8);
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.translate(pf.getImageableX(), pf.getImageableY());
			g2d.setColor(Color.black);
			g2d.setFont(fontS10B);
			g2d.drawRect(0, 5, Integer.parseInt(getInt(String.valueOf(pf.getWidth()))), 2);
			g2d.drawString("LISTA DE PRECIOS EMILIO A. LORIS AL " + DateUtils.convertDateToString(new Date()), 0, 17);
			g2d.drawRect(0, 20, Integer.parseInt(getInt(String.valueOf(pf.getWidth()))), 2);
			
			int yPos = 35;
			int xPos = 10;
			
			for (int i = 0; i < this.articulos.size(); ) {
				Articulo articuloAnt = this.articulos.get(i);
				Articulo articuloAct = null;
				
				i++;
				if(i < this.articulos.size())
					articuloAct = this.articulos.get(i);
				
				g2d.setFont(fontS10B);
				//PRINT Familia
				g2d.drawString(articuloAnt.getFamilia().getCodigo().toString() + "  " + articuloAnt.getFamilia().getDescripcion(), xPos, yPos);
				g2d.setFont(fontS10);
				yPos += PULGADA * 1.5;
				//PRINT Marca
				g2d.drawString(articuloAnt.getMarca().getId().toString() + "  " + articuloAnt.getMarca().getDescripcion(), xPos + 10, yPos);
				g2d.setFont(fontS8);
				yPos += PULGADA * 1.5;
				//PRINT Articulo
				g2d.drawString(articuloAnt.getCodigo(), xPos, yPos);
				g2d.drawString(articuloAnt.getDescripcion(), xPos + 30, yPos);
				g2d.drawString(getAmount(articuloAnt.getPrecioVenta(), 8), xPos + 170, yPos);
				
				for( ; articuloAct != null && articuloAnt.getFamilia().getCodigo().equals(articuloAct.getFamilia().getCodigo()); ){
					yPos += PULGADA;
					g2d.drawString(articuloAct.getCodigo(), xPos, yPos);
					g2d.drawString(articuloAct.getDescripcion(), xPos + 30, yPos);
					g2d.drawString(getAmount(articuloAct.getPrecioVenta(), 8), xPos + 170, yPos);
					i++;
					if(i < this.articulos.size())
						articuloAct = this.articulos.get(i);
					else
						articuloAct = null;
				}
				
				yPos += PULGADA * 2;
				if(yPos >= 800){
					yPos = 35;
					xPos = 300;
				}
			}
									

			return Printable.PAGE_EXISTS;
		} else {
			return Printable.NO_SUCH_PAGE;
		}
	}
}