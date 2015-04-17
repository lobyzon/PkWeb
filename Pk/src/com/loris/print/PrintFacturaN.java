package com.loris.print;

import java.awt.Color;
import java.awt.Font;
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

import com.loris.bean.Totales;
import com.loris.domain.Factura;
import com.loris.domain.FacturaType;
import com.loris.domain.ItemFactura;
import com.loris.domain.Params;
import com.loris.utils.DateUtils;

public class PrintFacturaN extends AbstractPrint implements Printable {	
	private Factura factura;
	private Totales totales;
	private Params params;

	public PrintFacturaN(Factura facturaParam, Totales totales, Params params, int printerServiceIndex) {		

		this.factura = facturaParam;
		this.totales = totales;
		this.params = params;
		
		//A4: 595 x 842. 21 cm x 29.7 cm		
		PageFormat pf = new PageFormat();
		Paper paper = new Paper();
		paper.setSize(595, 842);
		paper.setImageableArea(0, 0, 595, 842);
		pf.setPaper(paper);
		
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(new MediaPrintableArea(0, 20, 210, 297, MediaPrintableArea.MM));		
		aset.add(OrientationRequested.PORTRAIT);
		aset.add(new Copies(getCopies()));
		aset.add(new JobName("Impresion Factura", null));				
		
		/* Create a print job */
		PrinterJob pj = PrinterJob.getPrinterJob();		
		pj.setPrintable(this, pf);
		
		//PRINT
		try {						
				pj.setPrintService(PrinterJob.lookupPrintServices()[printerServiceIndex]);
				pj.print(aset);
		} catch (PrinterException pe) {
			System.err.println(pe);
		}
		
	}	

	private int getCopies() {
		if(FacturaType.FACTURA_N_TYPE.equals(this.factura.getFacturaType().getFacturaTypeId()))
			return this.params.getCantCopiasFacturaN();
		return this.params.getCantCopiasFactura();
	}

	public int print(Graphics g, PageFormat pf, int pageIndex) {
		final int PULGADA = 14;
		if (pageIndex == 0) {
			Font fontS10 = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
			Font fontS10B = new Font(Font.SANS_SERIF, Font.BOLD, 10);
			Font fontS8 = new Font(Font.SANS_SERIF, Font.PLAIN, 8);			
			Font fontCourier8 = new Font(COURIER_FONT, Font.PLAIN, 8);
			Font fontCourier9 = new Font(COURIER_FONT, Font.PLAIN, 9);
			Font fontS10BTotal = new Font(COURIER_FONT, Font.BOLD, 10);
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.translate(pf.getImageableX(), pf.getImageableY());
			g2d.setColor(Color.black);
			g2d.setFont(fontS10B);			
			//Print DOC Title and Date
			g2d.drawString("NUMERO:   " + factura.getNroFactura(), 343, 25);
			g2d.setFont(fontS10);
			g2d.drawString(DateUtils.convertDateToString(factura.getFecha()), 473, 25);
			g2d.setFont(fontS8);
			//Print Cliente Data
			g2d.drawString(factura.getCliente().getId() + " " + factura.getCliente().getRazonSocial(), 20, 97 - PULGADA);
			g2d.drawString(factura.getCliente().getDireccion().getCalle() + " " + factura.getCliente().getDireccion().getNumero(), 20, 97);
			g2d.drawString("(" + factura.getCliente().getDireccion().getCodPostal() + ") - " + factura.getCliente().getDireccion().getLocalidad(), 20, 97 + PULGADA * 1);
			g2d.drawString(getCuit(factura.getCliente().getCuit()), 20, 97 + PULGADA * 2);			
			//Print Item Titles
			g2d.drawString("CODIGO", 30, 167);
			g2d.drawString("DESCRIPCION", 126, 167);
			g2d.drawString("CANT.", 408, 167);
			g2d.drawString("DESC %", 370 + PULGADA * 5, 167);
			g2d.drawString("PRECIO", 373 + PULGADA * 8, 167);
			g2d.drawString("IMPORTE", 355 + PULGADA * 12, 167);			
			
			//PRINT Items
			int y = 173;
			printItems(y, 14, g2d, fontCourier8, fontS8);
			
			//PRINT Totales
			g2d.setFont(fontCourier9);
			g2d.drawString("Subtotal", 460, 700);
			g2d.drawString(getAmount(this.factura.getSubTotal(), 8), 510, 700);						
			g2d.drawString("Dto.", 460, 712);
			g2d.drawString(getAmount(this.totales.getDescuentoTotal(), 8), 510, 712);
			g2d.drawString("Subtotal", 460, 724);
			g2d.drawString(getAmount(this.totales.getSubTotalDescontado(), 8), 510, 724);
			
			//Print Comentarios.
			g2d.drawRect(20, 680, 340, 70);
			g2d.setFont(fontS8);
			g2d.drawString("COMENTARIOS:", 25, 690);			
			g2d.setFont(fontCourier8);
			printComentarios(g2d, factura, 700, 25);
			
			//Print Total.BOLD
			g2d.setFont(fontS10BTotal);
			g2d.drawString("TOTAL", 464, 760);
			g2d.drawString(getAmount(this.totales.getTotal(), 8), 510, 760);
			
			return Printable.PAGE_EXISTS;
		} else {
			return Printable.NO_SUCH_PAGE;
		}
	}

	private void printItems(int y, int PULGADA, Graphics2D g2d, Font courier, Font sansSerif) {
		for (ItemFactura item : factura.getItems()) {
			y += PULGADA;
			g2d.setFont(courier);
			g2d.drawString(	getNormalizedWidthCode(item.getArticulo().getMarca().getId().toString(), 3) + " " + 
				getNormalizedWidthCode(item.getArticulo().getFamilia().getCodigo().toString(), 3) + " " + 
				getNormalizedWidthCode(item.getArticulo().getCodigo(), 6), 20, y);
			
			g2d.setFont(sansSerif);			
			g2d.drawString(item.getArticulo().getFamilia().getDescripcion().toUpperCase() + "   " +
				item.getArticulo().getDescripcion() + "   " + 
				item.getArticulo().getMarca().getDescripcion().toUpperCase(), 92, y);
			
			g2d.setFont(courier);
			g2d.drawString(getNormalizedWidthCode(item.getCantidad().toString(), 6), 402, y);
			g2d.drawString(getAmount(item.getDescuento(), 4), 373 + PULGADA * 5, y);
			g2d.drawString(getAmount(item.getArticulo().getPrecioVenta(), 8), 363 + PULGADA * 8, y);
			g2d.drawString(getAmount(item.getTotalItem(), 8), 352 + PULGADA * 12, y);
		}									
	}		
}