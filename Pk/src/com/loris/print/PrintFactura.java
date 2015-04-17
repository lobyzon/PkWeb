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
import com.loris.domain.Cliente;
import com.loris.domain.Factura;
import com.loris.domain.FacturaType;
import com.loris.domain.ItemFactura;
import com.loris.domain.Params;
import com.loris.utils.DateUtils;

public class PrintFactura extends AbstractPrint implements Printable {
	final String INSCRIPTO = "S";
	private Factura factura;
	private Totales totales;
	private Params params;

	public PrintFactura(Factura facturaParam, Totales totales, Params params) {		

		this.factura = facturaParam;
		this.totales = totales;
		this.params = params;
		
		//A4: 595 x 842. 21 cm x 29.7 cm
		//Custom Format Pre-Printed Paper: Alto: 20.6 cm. Ancho: 20.5 cm. 583 x 584
		PageFormat pf = new PageFormat();
		Paper paper = new Paper();
		paper.setSize(583, 584);
		paper.setImageableArea(0, 0, 583, 584);
		pf.setPaper(paper);
		
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(new MediaPrintableArea(0, 20, 215, 203, MediaPrintableArea.MM));
		aset.add(OrientationRequested.PORTRAIT);
		aset.add(new Copies(getCopies()));
		aset.add(new JobName("Impresion Factura", null));		

		/* Create a print job */
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setPrintable(this, pf);
		PrintService printServices [] = PrinterJob.lookupPrintServices();
		//PRINT
		try {			
			pj.setPrintService(getMatrixPrinter(printServices));			
			pj.print(aset);
		} catch (PrinterException pe) {
			System.err.println(pe);
		}
		
	}	

	private PrintService getMatrixPrinter(PrintService[] printServices) {
		for(int i = 0; i < printServices.length; i++){
			if(printServices[i].getName().contains(params.getMatrixPrinterName()))
				return printServices[i];
		}
		return null;
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
			g2d.drawString(DateUtils.convertDateToString(factura.getFecha()), 473, 10);
			g2d.setFont(fontS8);
			//Print Cliente Data
			g2d.setFont(fontS9);
			g2d.drawString(factura.getCliente().getId() + " " + factura.getCliente().getRazonSocial(), 25, 97 - 12);
			g2d.drawString(factura.getCliente().getDireccion().getCalle() + " " + factura.getCliente().getDireccion().getNumero(), 25, 97);
			g2d.drawString("(" + factura.getCliente().getDireccion().getCodPostal() + ") - " + factura.getCliente().getDireccion().getLocalidad(), 25, 97 + 12);
			g2d.drawString(getCuit(factura.getCliente().getCuit()), 25, 97 + (12 * 2));
			//Print Responsable Insc.
			printRespInscripto(factura.getCliente(), g2d);
			//Print Item Titles
			g2d.setFont(fontS8);
			g2d.drawString("CODIGO", 20, 167);
			g2d.drawString("DESCRIPCION", 126, 167);
			g2d.drawString("CANTIDAD", 369, 167);
			g2d.drawString("BONIF %", 349 + PULGADA * 5, 167);
			g2d.drawString("PRECIO", 357 + PULGADA * 8, 167);
			g2d.drawString("IMPORTE", 350 + PULGADA * 12, 167);		
			
			//PRINT Items
			int y = 173;
			printItems(y, 10, PULGADA, g2d, fontCourier8, fontS8);
			
			//PRINT Totales
			g2d.setFont(fontCourier8);
			g2d.drawString(getAmount(this.factura.getSubTotal(), 8), 506, 400);
			g2d.drawString(getAmount(this.totales.getDescuentoTotal(), 8), 506, 412);
			g2d.drawString(getAmount(this.totales.getSubTotalDescontado(), 8), 506, 424);			
			g2d.drawString(getAmount(params.getIvaInscripto().getIVA(), 4), 404, 436);
			g2d.drawString(getAmount(this.totales.getIva(), 8), 506, 436);
			
			//Print Comentarios.
			printComentarios(g2d, factura, 400, 20);
			
			//Print Total.BOLD
			g2d.setFont(fontCourier10B);
			g2d.drawString(getAmount(this.totales.getTotal(), 8), 500, 470);			
			
			return Printable.PAGE_EXISTS;
		} else {
			return Printable.NO_SUCH_PAGE;
		}
	}	

	private void printDocTitle(Graphics2D g2d, Integer facturaTypeId) {
		if(FacturaType.FACTURA_TYPE.equals(facturaTypeId))
			g2d.drawString("FACTURA", 343, 10);
		else if(FacturaType.FACTURA_NC_TYPE.equals(facturaTypeId))
			g2d.drawString("NOTA DE CREDITO", 338, 10);		
	}

	private void printRespInscripto(Cliente cliente, Graphics2D g2d) {
		if(INSCRIPTO.equals(cliente.getResponsableInsc()))
			g2d.drawString("X", 541, 87);		
	}
	
	private void printItems(int y, int newLine, int pulgada, Graphics2D g2d, Font courier, Font fontLetras) {
		for (ItemFactura item : factura.getItems()) {
			y += newLine;
			g2d.setFont(courier);
			g2d.drawString(	getNormalizedWidthCode(item.getArticulo().getMarca().getId().toString(), 3) + " " + 
				getNormalizedWidthCode(item.getArticulo().getFamilia().getCodigo().toString(), 3) + " " + 
				getNormalizedWidthCode(item.getArticulo().getCodigo(), 6), 0, y);
			g2d.setFont(fontLetras);
			String cadenaLetras = item.getArticulo().getFamilia().getDescripcion().toUpperCase() + "  " +
			item.getArticulo().getDescripcion() + "  ";
			if(cadenaLetras.length() <= 70)
				cadenaLetras += item.getArticulo().getMarca().getDescripcion().toUpperCase(); 
			g2d.drawString(cadenaLetras, 75, y);
			
			g2d.setFont(courier);
			g2d.drawString(getNormalizedWidthCode(item.getCantidad().toString(), 6), 352 + pulgada, y);
			g2d.drawString(getAmount(item.getDescuento(), 4), 337 + pulgada * 5, y);
			g2d.drawString(getAmount(item.getArticulo().getPrecioVenta(), 8), 344 + pulgada * 8, y);
			g2d.drawString(getAmount(item.getTotalItem(), 8), 340 + pulgada * 12, y);
		}									
	}
}