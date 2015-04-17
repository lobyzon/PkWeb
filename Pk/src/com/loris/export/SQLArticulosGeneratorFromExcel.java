package com.loris.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import org.apache.commons.lang.StringUtils;

public class SQLArticulosGeneratorFromExcel extends AbstractExportFromExcel{		
	
	public void generateFileOutputContent() {
		FileWriter fstream = null;
		BufferedWriter out = null;
		Workbook workbook = null;
		WorkbookSettings ws = new WorkbookSettings();
	    ws.setLocale(new Locale("es", "ES"));
	    ws.setSuppressWarnings(true);
	    	    
		try {
			workbook = Workbook.getWorkbook(new File("G:\\Software\\JAVA\\Pk Produccion\\Lista\\ListaLorisMail.xls"));
			fstream = new FileWriter("c:\\PkWeb\\Lista\\UPDATE_ARTICULOS.sql");
	        out = new BufferedWriter(fstream);	    
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (BiffException e) {			
			e.printStackTrace();
		}
		
		Sheet sheet = workbook.getSheet("LISTA");
		
		//Add rows from DB Articulos
		int rowIndex = 24;
		int blankCells = 0;
		String code = "";
		Cell cell;
		String insert = "";
		
		try {
			for( ; blankCells < 8 && rowIndex < sheet.getRows(); rowIndex++){
				cell = sheet.getCell(6, rowIndex);				
				code = cell.getContents();
				
				if(StringUtils.isNotBlank(code)){
					blankCells = 0;
//					insert = "INSERT INTO pk.articulo (codigo, descripcion, fecha_modif, precio_costo, " +
//									"precio_venta, stock, stock_min, familia_id_fk, marca_id_fk) " +
//									"values (" + getCodigo(code) + ", " + getDescripcion(sheet, rowIndex) + ", "
//									+ getFechaModif(sheet, rowIndex) + ", 2, " + getPrecioVenta(sheet, rowIndex) +
//									", 0, 0, " + getFamilia(code) + ", " + getMarca(code) + ");";
					insert = "UPDATE pk.articulo set descripcion = " + getDescripcion(sheet, rowIndex) + 
							" WHERE codigo = " + getCodigo(code) + " AND marca_id_fk = " + getMarca(code) + 
							" AND FAMILIA_ID_FK = " + getFamilia(code) + ";";
										
						out.write(insert);
						out.newLine();					
				}else
					blankCells++;
			}
			workbook.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
			
	public static void main(String[] args) {
		SQLArticulosGeneratorFromExcel sql = new SQLArticulosGeneratorFromExcel();
		sql.generateFileOutputContent();		
	}
}
