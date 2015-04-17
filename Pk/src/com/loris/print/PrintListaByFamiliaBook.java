package com.loris.print;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;

import com.loris.bean.Printer;
import com.loris.domain.Articulo;

public class PrintListaByFamiliaBook extends AbstractPrint {

	private List<Articulo> articulos;
	private int indexFrom;
	private int indexArticulo;
	private int pageNum;	

	public PrintListaByFamiliaBook(List<Articulo> articulos, Printer printer) {

		this.articulos = articulos;
		this.indexFrom = 0;
		this.indexArticulo = 0;
		this.pageNum = 1;		
		boolean finPage;

		/* Create a print job */
		PrinterJob pj = PrinterJob.getPrinterJob();
		
		//A4: 595 x 842. 21 cm x 29.7 cm		
		PageFormat pf = new PageFormat();
		Paper paper = new Paper();
		paper.setSize(595, 842);
		paper.setImageableArea(10, 10, 595, 842);
		pf.setPaper(paper);					

		Book book = new Book();

		for (; this.indexArticulo < articulos.size();) {

			finPage = false;
			int yPos = 45;
			int xPos = 20;
			this.indexFrom = this.indexArticulo;
			
			for (int i = this.indexArticulo; this.indexArticulo < this.articulos.size() && !finPage;) {
				Articulo articuloAnt = this.articulos.get(i);
				Articulo articuloAct = null;				
				
				//FAMILIA
				if(printFamilia(articuloAnt, i, articulos)){
					if(isFinPage(xPos, yPos, 37)){						
						break;
					}
					//coordinates
					if(xPos != 300 && (yPos + 37) >= PAGE_HEIGHT){
						yPos = 45;
						xPos = 300;					
					}
				}
				
				//MARCA
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
				}else{
					if(isFinPage(xPos, yPos, 17)){						
						break;
					}
					//coordinates
					if(xPos != 300 && (yPos + 17) >= PAGE_HEIGHT){
						yPos = 46;
						xPos = 300;					
					}					
				}
				
				//ARTICULO
				if(yPos != 45 || (printMarcaByFamilia(articuloAnt, i, this.articulos) && !printFamilia(articuloAnt, i, this.articulos)))
					yPos += PULGADA * 1.5;
				
				i++;
				this.indexArticulo++;
				if (i < this.articulos.size())
					articuloAct = this.articulos.get(i);
				
				yPos += PULGADA;
				for (; articuloAct != null && articuloAnt.getFamilia().getCodigo().equals(articuloAct.getFamilia().getCodigo()) &&
						articuloAnt.getMarca().getId().equals(articuloAct.getMarca().getId()) && yPos < PAGE_HEIGHT;) {
					yPos += PULGADA;
					i++;
					this.indexArticulo++;
					if(i < this.articulos.size() && yPos < PAGE_HEIGHT){											
						articuloAct = this.articulos.get(i);
					}
					else
						articuloAct = null;
				}
				yPos += PULGADA;

				if (xPos == 300 && yPos >= PAGE_HEIGHT) {
					finPage = true;
				}

				if (yPos >= PAGE_HEIGHT) {
					yPos = 45;
					xPos = 300;
				}
			}
			// ADD NEW PAGE
			book.append(new PrintListaByFamiliaPage(articulos, indexFrom, indexArticulo, this.pageNum), pf);
			this.pageNum++;
		}
		
		pj.setPageable(book);
		//PRINT DIALOG, PRINT	
		try {
			pj.setPrintService(PrinterJob.lookupPrintServices()[printer.getPrinterServiceIndex()]);
			pj.setCopies(1);			
			pj.setJobName("Impresión Lista");
			pj.print();
		} catch (PrinterException pe) {
			System.err.println(pe);
		}
	}	
}