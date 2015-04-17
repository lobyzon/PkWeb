package com.loris.print;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.Date;
import java.util.List;

import com.loris.domain.Articulo;
import com.loris.utils.DateUtils;

public class PrintListaByFamiliaPage extends AbstractPrint implements Printable {

	private List<Articulo> articulos;	
	private int indexFrom;
	private int indexTo;
	private int pageNum;	
	
	public PrintListaByFamiliaPage(List<Articulo> articulos, int indexFrom, int indexTo, int pageNum) {		

		this.articulos = articulos;		
		this.indexFrom = indexFrom;
		this.indexTo = indexTo;
		this.pageNum = pageNum;		
	}	

	public int print(Graphics g, PageFormat pf, int pageIndex) {
		Font fontS10 = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
		Font fontS10B = new Font(Font.SANS_SERIF, Font.BOLD, 10);
		Font fontS11B = new Font(Font.SANS_SERIF, Font.BOLD, 11);
		Font fontS9 = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
		Font fontS9Courier = new Font(COURIER_FONT, Font.PLAIN, 9);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		g2d.setColor(Color.black);
		g2d.setFont(fontS10B);
		g2d.drawRect(0, 5, Integer.parseInt(getInt(String.valueOf(pf.getWidth()))), 2);
		String title1 = "LISTA DE PRECIOS EMILIO A. LORIS AL " + DateUtils.convertDateToString(new Date());
		String title2 = "Alsina 3030/38 - Florida CP(1602) Tel.Fax: 4730-2237/4760-3195  Email: emilioaloris@yahoo.com.ar"; 
		FontMetrics fontMetrics = g2d.getFontMetrics();
	    double title1X = (pf.getImageableWidth() / 2) - (fontMetrics.stringWidth(title1) / 2);	    
	    double pageIndexX =  pf.getImageableWidth() - fontMetrics.stringWidth("HOJA: " + this.pageNum) - (pf.getImageableX() * 3);
		g2d.drawString(title1, (int)title1X, 17);
		g2d.drawString("HOJA: " + this.pageNum, (int)pageIndexX, 17);
		g2d.setFont(fontS9);
		FontMetrics fontMetricsTitle2 = g2d.getFontMetrics();
		double title2X = (pf.getImageableWidth() / 2) - (fontMetricsTitle2.stringWidth(title2) / 2);
		g2d.drawString(title2, (int)title2X, 27);
		g2d.drawRect(0, 30, Integer.parseInt(getInt(String.valueOf(pf.getWidth()))), 2);
		
		int yPos = 45;
		int xPos = 20;
		boolean finPage = false;
		
		for (int i = this.indexFrom; i < this.indexTo && !finPage; ) {
			Articulo articuloAnt = this.articulos.get(i);
			Articulo articuloAct = null;
						
			//PRINT Familia			
			if(printFamilia(articuloAnt, i, this.articulos)){
				if(isFinPage(xPos, yPos, 37)){
					break;
				}
				//coordinates
				if(xPos != 300 && (yPos + 37) >= PAGE_HEIGHT){
					yPos = 45;
					xPos = 300;					
				}
				g2d.setFont(fontS11B);
				g2d.drawString(articuloAnt.getFamilia().getCodigo().toString() + "  " + articuloAnt.getFamilia().getDescripcion().toUpperCase(), xPos, yPos);				
			}			
			
			//PRINT Marca
			if(printMarcaByFamilia(articuloAnt, i, this.articulos) && printFamilia(articuloAnt, i, this.articulos)){				
				yPos += PULGADA * 1.5;
				if(isFinPage(xPos, yPos, 17)){
					break;
				}
				//coordinates
				if(xPos != 300 && (yPos + 17) >= PAGE_HEIGHT){
					yPos = 46;
					xPos = 300;					
				}
				g2d.setFont(fontS10);
				g2d.drawString(articuloAnt.getMarca().getId().toString() + "  " + articuloAnt.getMarca().getDescripcion().toUpperCase(), xPos + 10, yPos);
			}else if(printMarcaByFamilia(articuloAnt, i, this.articulos)){
				if(isFinPage(xPos, yPos, 17)){						
					break;
				}
				//coordinates
				if(xPos != 300 && (yPos + 17) >= PAGE_HEIGHT){
					yPos = 46;
					xPos = 300;					
				}
								
				g2d.setFont(fontS10);
				g2d.drawString(articuloAnt.getMarca().getId().toString() + "  " + articuloAnt.getMarca().getDescripcion().toUpperCase(), xPos + 10, yPos);
			}
			
			//PRINT Articulo			
			if(yPos != 45 || (printMarcaByFamilia(articuloAnt, i, this.articulos) && !printFamilia(articuloAnt, i, this.articulos)))
				yPos += PULGADA * 1.5;
			
			g2d.setFont(fontS9);
			g2d.drawString(articuloAnt.getCodigo(), xPos, yPos);
			g2d.drawString(articuloAnt.getDescripcion(), xPos + 35, yPos);			
			g2d.setFont(fontS9Courier);
			g2d.drawString(getAmount(articuloAnt.getPrecioVenta(), 8), xPos + 215, yPos);
			
			i++;
			if(i < this.articulos.size())
				articuloAct = this.articulos.get(i);			
			
			yPos += PULGADA;
			for( ; articuloAct != null && articuloAnt.getFamilia().getCodigo().equals(articuloAct.getFamilia().getCodigo()) &&
					articuloAnt.getMarca().getId().equals(articuloAct.getMarca().getId())&& yPos < PAGE_HEIGHT && i < indexTo; ){
				g2d.setFont(fontS9);
				g2d.drawString(articuloAct.getCodigo(), xPos, yPos);
				g2d.drawString(articuloAct.getDescripcion(), xPos + 35, yPos);				
				g2d.setFont(fontS9Courier);
				g2d.drawString(getAmount(articuloAct.getPrecioVenta(), 8), xPos + 215, yPos);
								
				yPos += PULGADA;
				i++;
				if(i < this.articulos.size() && yPos < PAGE_HEIGHT){										
					articuloAct = this.articulos.get(i);
				}
				else
					articuloAct = null;
			}
			
			yPos += PULGADA;
			
			if(xPos == 300 && yPos >= PAGE_HEIGHT){				
				finPage = true;
			}
			
			if(yPos >= PAGE_HEIGHT){
				yPos = 45;
				xPos = 300;					
			}
		}
		return Printable.PAGE_EXISTS;
	}	
}