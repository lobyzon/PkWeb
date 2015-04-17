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

import javax.print.PrintService;
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

public class PrintFacturaA4 extends AbstractPrint implements Printable {
	final String INSCRIPTO = "S";
	private Factura factura;
	private Totales totales;
	private Params params;
	private boolean laser;

	public PrintFacturaA4(Factura facturaParam, Totales totales, Params params, int printerServiceIndex) {		

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
			PrintService printService = PrinterJob.lookupPrintServices()[printerServiceIndex];
			this.laser = getIsLaser(printService.getName(), params);
			pj.setPrintService(printService);
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
			Font fontS7 = new Font(Font.SANS_SERIF, Font.PLAIN, 7);
			Font fontS9 = new Font(Font.SANS_SERIF, Font.PLAIN, 9);						
			
			Font fontCourier8 = new Font(COURIER_FONT, Font.PLAIN, 8);			
			Font fontCourier10B = new Font(COURIER_FONT, Font.BOLD, 10);
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.translate(pf.getImageableX(), pf.getImageableY());
			g2d.setColor(Color.black);
			g2d.setFont(fontS10B);			
			//Print DOC Title and Date
			printDocTitle(g2d, factura.getFacturaType().getFacturaTypeId());			
			g2d.setFont(fontS10);
			g2d.drawString(DateUtils.convertDateToString(factura.getFecha()), getLaserAdjust(this.laser, 384), 99);
			g2d.setFont(fontS8);
			//Print Client Data
			g2d.setFont(fontS9);
			g2d.drawString(factura.getCliente().getId() + " " + factura.getCliente().getRazonSocial(), getLaserAdjust(this.laser, 64), 178 - 12);
			g2d.drawString(factura.getCliente().getDireccion().getCalle() + " " + factura.getCliente().getDireccion().getNumero(), getLaserAdjust(this.laser, 64), 178);
			g2d.drawString("(" + factura.getCliente().getDireccion().getCodPostal() + ") - " + factura.getCliente().getDireccion().getLocalidad(), getLaserAdjust(this.laser, 64), 178 + 12);
			g2d.drawString(getCuit(factura.getCliente().getCuit()), getLaserAdjust(this.laser, 340), 201);
			//Print Condicion de Venta
			g2d.drawString("VENTA CONTADO", getLaserAdjust(this.laser, 144), 222);
			//Print Item Titles
			g2d.setFont(fontS8);
			g2d.drawString("CODIGO", getLaserAdjust(this.laser, 20), 242);
			g2d.drawString("DESCRIPCION", getLaserAdjust(this.laser, 160), 242);
			g2d.drawString("CANT.", getLaserAdjust(this.laser, 414), 242);
			g2d.drawString("DESC %", getLaserAdjust(this.laser, 376) + PULGADA * 5, 242);
			g2d.drawString("PRECIO", getLaserAdjust(this.laser, 373) + PULGADA * 8, 242);
			g2d.drawString("IMPORTE", getLaserAdjust(this.laser, 355) + PULGADA * 12, 242);		
			
			//PRINT Items
			int y = 252;
			printItems(y, 11, PULGADA, g2d, fontCourier8, fontS8);
			
			//PRINT Totales
			g2d.setFont(fontCourier8);
			g2d.drawString(getAmount(this.factura.getSubTotal(), 8), getLaserAdjust(this.laser, 455), 681);
			g2d.drawString(getAmount(this.totales.getDescuentoTotal(), 8), getLaserAdjust(this.laser, 455), 694);
			g2d.drawString(getAmount(this.totales.getSubTotalDescontado(), 8), getLaserAdjust(this.laser, 455), 709);			
			g2d.drawString(getAmount(params.getIvaInscripto().getIVA(), 4), getLaserAdjust(this.laser, 363), 724);
			g2d.drawString(getAmount(this.totales.getIva(), 8), getLaserAdjust(this.laser, 455), 724);
			
			//Print Comentarios.			
			g2d.setFont(fontS8);
			g2d.drawString("COMENTARIOS:", 32, 682);			
			g2d.setFont(fontCourier8);
			printComentarios(g2d, factura, 692, 32);
			
			//Print Total.BOLD
			g2d.setFont(fontCourier10B);
			g2d.drawString(getAmount(this.totales.getTotal(), 8), getLaserAdjust(this.laser, 448), 737);			
			
			return Printable.PAGE_EXISTS;
		} else {
			return Printable.NO_SUCH_PAGE;
		}
	}	

	private void printDocTitle(Graphics2D g2d, Integer facturaTypeId) {		
		if(FacturaType.FACTURA_NC_TYPE.equals(facturaTypeId)){
			g2d.drawString("NOTA DE CREDITO", 448, getLaserAdjust(this.laser, 24));
			g2d.drawRect(getLaserAdjust(this.laser, 336), 30, 128, 1);
		}
	}	
	
	private void printItems(int y, int newLine, int pulgada, Graphics2D g2d, Font courier, Font fontLetras) {
		for (ItemFactura item : factura.getItems()) {		
			y += newLine;
			g2d.setFont(courier);
			g2d.drawString(	getNormalizedWidthCode(item.getArticulo().getMarca().getId().toString(), 3) + " " + 
				getNormalizedWidthCode(item.getArticulo().getFamilia().getCodigo().toString(), 3) + " " + 
				getNormalizedWidthCode(item.getArticulo().getCodigo(), 6), getLaserAdjust(this.laser, 11), y);
			
			g2d.setFont(fontLetras);			
			g2d.drawString(item.getArticulo().getFamilia().getDescripcion().toUpperCase() + "  " +
				item.getArticulo().getDescripcion() + "  " + 
				item.getArticulo().getMarca().getDescripcion().toUpperCase(), getLaserAdjust(this.laser, 84), y);
			
			g2d.setFont(courier);
			g2d.drawString(getNormalizedWidthCode(item.getCantidad().toString(), 6), getLaserAdjust(this.laser, 408), y);
			g2d.drawString(getAmount(item.getDescuento(), 4), getLaserAdjust(this.laser, 378) + pulgada * 5, y);
			g2d.drawString(getAmount(item.getArticulo().getPrecioVenta(), 8), getLaserAdjust(this.laser, 363) + pulgada * 8, y);
			g2d.drawString(getAmount(item.getTotalItem(), 8), getLaserAdjust(this.laser, 352) + pulgada * 12, y);
		}
	}
}