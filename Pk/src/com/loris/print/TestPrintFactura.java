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
import java.util.Date;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;

import com.loris.bean.Totales;
import com.loris.domain.Cliente;
import com.loris.domain.Direccion;
import com.loris.domain.Factura;
import com.loris.domain.FacturaType;
import com.loris.utils.DateUtils;

public class TestPrintFactura extends AbstractPrint implements Printable {
	private boolean laser;
	
	public TestPrintFactura() {
	
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
			laser = getIsLaser(printService.getName()); 
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
		int y = 252;
		final int PULGADA = 14;		
		
		if (pageIndex == 0) {
			//Fonts
			Font fontS10 = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
			Font fontS10B = new Font(Font.SANS_SERIF, Font.BOLD, 10);
			Font fontS8 = new Font(Font.SANS_SERIF, Font.PLAIN, 8);
			Font fontS7 = new Font(Font.SANS_SERIF, Font.PLAIN, 7);
			Font fontS9 = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
			Font fontCourier8 = new Font(COURIER_FONT, Font.PLAIN, 8);			
			Font fontCourier10B = new Font(COURIER_FONT, Font.BOLD, 10);
			
			Direccion direccion = new Direccion();
			direccion.setCalle("Cabildo");
			direccion.setNumero("1500");
			direccion.setCodPostal("1428");
			direccion.setLocalidad("Capital Federal");
			
			Cliente cliente = new Cliente();
			cliente.setId(new Integer(250));
			cliente.setRazonSocial("Ricardo Casanova");
			cliente.setDireccion(direccion);
			cliente.setCuit("20310758680");
			
			Factura factura = new Factura();
			FacturaType facturaType = new FacturaType(FacturaType.FACTURA_TYPE);
			factura.setFacturaType(facturaType);
			factura.setFecha(new Date());
			factura.setSubTotal(new BigDecimal("369.15"));
			factura.setCliente(cliente);
			
			Totales totales = new Totales();
			totales.setDescuentoTotal(new BigDecimal("25.15"));
			totales.setSubTotal(new BigDecimal("349.23"));
			totales.setSubTotalDescontado(new BigDecimal("345.25"));
			totales.setIva(new BigDecimal("25.15"));
			totales.setTotal(new BigDecimal("1000.15"));
			
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.translate(pf.getImageableX(), pf.getImageableY());
			
			//Print DOC Title and Date
			printDocTitle(g2d, factura.getFacturaType().getFacturaTypeId());			
			g2d.setFont(fontS10);
			g2d.drawString(DateUtils.convertDateToString(factura.getFecha()), getLaserAdjust(laser, 384), 99);
			g2d.setFont(fontS8);
			//Print Client Data
			g2d.setFont(fontS9);
			g2d.drawString(factura.getCliente().getId() + " " + factura.getCliente().getRazonSocial(), getLaserAdjust(laser, 64), 178 - 12);
			g2d.drawString(factura.getCliente().getDireccion().getCalle() + " " + factura.getCliente().getDireccion().getNumero(), getLaserAdjust(laser, 64), 178);
			g2d.drawString("(" + factura.getCliente().getDireccion().getCodPostal() + ") - " + factura.getCliente().getDireccion().getLocalidad(), getLaserAdjust(laser, 64), 178 + 12);
			g2d.drawString(getCuit(factura.getCliente().getCuit()), getLaserAdjust(laser, 340), 201);
			//Print Condicion de Venta
			g2d.drawString("VENTA CONTADO", getLaserAdjust(laser, 144), 222);
			//Print Item Titles
			g2d.setFont(fontS8);
			g2d.drawString("CODIGO", getLaserAdjust(laser, 20), 242);
			g2d.drawString("DESCRIPCION", getLaserAdjust(laser, 160), 242);
			g2d.drawString("CANT.", getLaserAdjust(laser, 414), 242);
			g2d.drawString("DESC %", getLaserAdjust(laser, 376) + PULGADA * 5, 242);
			g2d.drawString("PRECIO", getLaserAdjust(laser, 373) + PULGADA * 8, 242);
			g2d.drawString("IMPORTE", getLaserAdjust(laser, 355) + PULGADA * 12, 242);
			
			for(int i = 1; i <= 30; i++){
				y += 11;
				if(i == 1 || i == 34){			
					g2d.setFont(fontCourier8);
					g2d.drawString(	"123" + " " + "321" + " " + "123456 ", getLaserAdjust(laser, 11), y);
					
					g2d.setFont(fontS8);
					String cadenaLetras = "FAMILIA DE UNOS CARACTERE" + "  " +
					"Descripción de unos 30 caracte" + "  ";
					
					cadenaLetras += "MARCA DE 16 CAR.";
					g2d.drawString(cadenaLetras, getLaserAdjust(laser, 84), y);
					
					g2d.setFont(fontCourier8);
					g2d.drawString(getNormalizedWidthCode("1000", 6), getLaserAdjust(laser, 408), y);
					g2d.drawString(getAmount(new BigDecimal("35.00"), 4), getLaserAdjust(laser, 378) + PULGADA * 5, y);
					g2d.drawString(getAmount(new BigDecimal("1000.00"), 8), getLaserAdjust(laser, 363) + PULGADA * 8, y);
					g2d.drawString(getAmount(new BigDecimal("3000.00"), 8), getLaserAdjust(laser, 352) + PULGADA * 12, y);
				}
			}			
			
			//PRINT Totales
			g2d.setFont(fontCourier8);
			g2d.drawString(getAmount(factura.getSubTotal(), 8), getLaserAdjust(laser, 455), 681);
			g2d.drawString(getAmount(totales.getDescuentoTotal(), 8), getLaserAdjust(laser, 455), 694);
			g2d.drawString(getAmount(totales.getSubTotalDescontado(), 8), getLaserAdjust(laser, 455), 709);			
			g2d.drawString(getAmount(new BigDecimal("21.00"), 4), getLaserAdjust(laser, 363), 724);
			g2d.drawString(getAmount(totales.getIva(), 8), getLaserAdjust(laser, 455), 724);
			
			//Print Comentarios			
			g2d.setFont(fontS8);			
			g2d.drawString("COMENTARIOS:", getLaserAdjust(laser, 32), 682);			
			factura.setComentarios("Cheque:   14-05-2011 $ 2000");
			g2d.setFont(fontCourier8);
			printComentarios(g2d, factura, getLaserAdjust(laser, 692), 32);
			
			//Print Total.BOLD
			g2d.setFont(fontCourier10B);
			g2d.drawString(getAmount(totales.getTotal(), 8), getLaserAdjust(laser, 448), 737);
			
			return Printable.PAGE_EXISTS;
		} else {
			return Printable.NO_SUCH_PAGE;
		}		
	}	
	
	private void printDocTitle(Graphics2D g2d, Integer facturaTypeId) {		
		if(FacturaType.FACTURA_NC_TYPE.equals(facturaTypeId)){
			g2d.drawString("NOTA DE CREDITO", 448, getLaserAdjust(laser, 24));
			g2d.drawRect(getLaserAdjust(laser, 336), 30, 128, 1);
		}
	}
	
	// Método para imprimir Matriz de impresión
//	public int print(Graphics g, PageFormat pf, int pageIndex) {		
//		if (pageIndex == 0) {			
//			Font fontSansSerif8 = new Font(Font.SANS_SERIF, Font.PLAIN, 8);						
//			
//			Graphics2D g2d = (Graphics2D) g;
//			g2d.translate(pf.getImageableX(), pf.getImageableY());
//			
//			g2d.setFont(fontSansSerif8);
//			
//			for(int y = 8; y < 842; y += 8){
//				g2d.drawString(String.valueOf(y), 0, y);
//				g2d.drawLine(0, y, 595, y);
//			}
//			
//			for(int x = 16; x < 595; x += 16){
//				g2d.drawString(String.valueOf(x), x, 8);
//				g2d.drawLine(x, 0, x, 842);
//			}
//						
//			return Printable.PAGE_EXISTS;
//		} else {
//			return Printable.NO_SUCH_PAGE;
//		}
//	}
		
	public static void main(String[] args) {
		new TestPrintFactura();
	}
}