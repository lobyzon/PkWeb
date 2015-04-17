package com.loris.export;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.loris.domain.Articulo;

public class ListaExcelExport {
	
	private static final String EMPTY = "";
	private static final java.text.DateFormat cellDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public static byte[] generateFileOutputContent(List<Articulo> articulos, String url) {
		WritableWorkbook workbook = null;
		WorkbookSettings ws = new WorkbookSettings();
	    ws.setLocale(new Locale("es", "ES"));
	    ws.setSuppressWarnings(true);	    
	    
	    
	    
	    final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			workbook = Workbook.createWorkbook(bos,Workbook.getWorkbook(new File(url)));
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WritableSheet sheet = workbook.getSheet("LISTA");
										
		byte[] output = null;
		
		try {			
			//Column Format
			//Fuente Azul, Fondo Gris
			WritableFont arial10BlueFontBold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD); 				
			arial10BlueFontBold.setColour(Colour.LIGHT_BLUE);
			WritableCellFormat arial10BlueFGreyBFormatBold = new WritableCellFormat (arial10BlueFontBold);
			arial10BlueFGreyBFormatBold.setBackground(Colour.GREY_25_PERCENT);
			
			WritableFont arial10DarkBlueFontBold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD); 				
			arial10DarkBlueFontBold.setColour(Colour.DARK_BLUE);
			WritableCellFormat arial10DarkBlueFGreyBFormatBold = new WritableCellFormat (arial10DarkBlueFontBold);
			arial10DarkBlueFGreyBFormatBold.setBackground(Colour.GREY_25_PERCENT);
			
			WritableFont arial10Font = new WritableFont(WritableFont.ARIAL, 10);		
			WritableCellFormat arial10FormatLeft = new WritableCellFormat (arial10Font);
			WritableCellFormat arial10GreyBFormatCenter = new WritableCellFormat (arial10Font);
			arial10GreyBFormatCenter.setBackground(Colour.GREY_25_PERCENT);
			
			WritableFont arial10FontG = new WritableFont(WritableFont.ARIAL, 10);
			arial10FontG.setColour(Colour.GREY_25_PERCENT);
			WritableCellFormat arial10GreyBFFormatCenter = new WritableCellFormat (arial10FontG);
			arial10GreyBFFormatCenter.setBackground(Colour.GREY_25_PERCENT);
			
			WritableFont arial10FontCenter = new WritableFont(WritableFont.ARIAL, 10);		
			WritableCellFormat arial10FormatCenter = new WritableCellFormat (arial10FontCenter);
			
			WritableFont arial10RedFontCenter = new WritableFont(WritableFont.ARIAL, 10);		
			arial10RedFontCenter.setColour(Colour.RED);
			WritableCellFormat arial10RedFormatCenter = new WritableCellFormat (arial10RedFontCenter);			
			
			WritableFont arial10FontCenterWhite = new WritableFont(WritableFont.ARIAL, 10);
			arial10FontCenterWhite.setColour(Colour.WHITE);
			WritableCellFormat arial10FormatCenterWhite = new WritableCellFormat (arial10FontCenterWhite);			
												
			//Set Alignments
			arial10BlueFGreyBFormatBold.setAlignment(Alignment.CENTRE);
			arial10FormatCenter.setAlignment(Alignment.CENTRE);
			arial10RedFormatCenter.setAlignment(Alignment.CENTRE);
			arial10FormatLeft.setAlignment(Alignment.LEFT);
			arial10GreyBFormatCenter.setAlignment(Alignment.CENTRE);
			
			//Add rows from DB Articulos
			int rowIndex = 22;
			int rowColumn = 0;
			int indexArticulo = 0;
			
			//Set Modification DATE			
			WritableFont arial12DarkBlueFontBold = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD); 				
			arial12DarkBlueFontBold.setColour(Colour.DARK_BLUE);
			WritableCellFormat arial12DarkBlueFBlueBFormatBold = new WritableCellFormat (arial12DarkBlueFontBold);
			arial12DarkBlueFBlueBFormatBold.setBackground(Colour.PALE_BLUE);

			sheet.addCell(new Label(1, 17, formatDate(new Date()), arial12DarkBlueFBlueBFormatBold));
			
			for(; indexArticulo < articulos.size();){
				Articulo articuloAnt = articulos.get(indexArticulo);
				Articulo articuloAct = null;
				
				//Print Titles
				//First Line
				sheet.addCell(new Label(rowColumn++, rowIndex, articuloAnt.getFamilia().getDescripcion(), arial10GreyBFFormatCenter));
				sheet.addCell(new Label(rowColumn++, rowIndex, articuloAnt.getFamilia().getDescripcion() + " - " + articuloAnt.getMarca().getDescripcion(), arial10BlueFGreyBFormatBold));
				sheet.addCell(new Label(rowColumn++, rowIndex, "", arial10BlueFGreyBFormatBold));
				sheet.addCell(new Label(rowColumn++, rowIndex, "", arial10BlueFGreyBFormatBold));
				sheet.addCell(new Label(rowColumn++, rowIndex, articuloAnt.getMarca().getDescripcion(), arial10FormatCenterWhite));
				
				//Second Line
				rowIndex++;
				rowColumn = 0;
				sheet.addCell(new Label(rowColumn++, rowIndex, articuloAnt.getFamilia().getDescripcion(), arial10GreyBFFormatCenter));
				sheet.addCell(new Label(rowColumn++, rowIndex, "Detalle", arial10GreyBFormatCenter));
				sheet.addCell(new Label(rowColumn++, rowIndex, "Precio", arial10GreyBFormatCenter));
				sheet.addCell(new Label(rowColumn++, rowIndex, "", arial10GreyBFFormatCenter));
				sheet.addCell(new Label(rowColumn++, rowIndex, articuloAnt.getMarca().getDescripcion(), arial10FormatCenterWhite));
				sheet.addCell(new Label(rowColumn++, rowIndex, "", arial10FormatCenterWhite));				
				
				if(indexArticulo < articulos.size())
					articuloAct = articulos.get(indexArticulo);
				
				rowIndex++;
				rowColumn = 0;
				for (; articuloAct != null && articuloAnt.getFamilia().getCodigo().equals(articuloAct.getFamilia().getCodigo()) &&
					articuloAnt.getMarca().getId().equals(articuloAct.getMarca().getId()) ;) {
										
					sheet.addCell(new Label(rowColumn++, rowIndex, toLowerCase(articuloAct.getFamilia().getDescripcion()), arial10FormatLeft));
					sheet.addCell(new Label(rowColumn++, rowIndex, articuloAct.getDescripcion(), arial10FormatLeft));
					sheet.addCell(new Label(rowColumn++, rowIndex, articuloAct.getPrecioVenta().toString(), arial10FormatCenter));
					
					if(articuloAct.getFechaModifVenta() != null)
						sheet.addCell(new Label(rowColumn, rowIndex, formatDate(articuloAct.getFechaModifVenta()), getFechaModifFormat(articuloAct.getFechaModifVenta(), arial10FormatCenter, arial10RedFormatCenter)));
					
					rowColumn++;
					
					sheet.addCell(new Label(rowColumn++, rowIndex, articuloAct.getMarca().getDescripcion(), arial10FormatCenterWhite));
					rowColumn++;
					sheet.addCell(new Label(rowColumn++, rowIndex, getCodigo(articuloAct), arial10FormatCenter));					
					
					rowIndex++;
					rowColumn = 0;
					indexArticulo++;
					if(indexArticulo < articulos.size())
						articuloAct = articulos.get(indexArticulo);
					else
						articuloAct = null;
				}				
			}
			
			//Save files
			workbook.write();			
			workbook.close();
			
			output = bos.toByteArray();
			bos.close();
			
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	private static String toLowerCase(String descripcion) {
		if(StringUtils.isNotBlank(descripcion)){
			String firstLetter = descripcion.substring(0, 1);
			String lowerString = StringUtils.lowerCase(descripcion).substring(1);
			
			return firstLetter + lowerString;
		}
		return "";
	}

	private static String getCodigo(Articulo articuloAct) {		
		return articuloAct.getMarca().getId() + "-" + articuloAct.getFamilia().getCodigo() 
			+ "-" + articuloAct.getCodigo();
	}

	private static CellFormat getFechaModifFormat(Date fechaModificacion, WritableCellFormat arial10, WritableCellFormat arial10Red) {		
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.MONTH, -1);
		
		Date dateMonthAgo = new Date();
		try {
			dateMonthAgo = cellDateFormat.parse(cellDateFormat.format(new Date(calendar.getTimeInMillis())));
		} catch (ParseException e) {			
			return arial10;
		}
		
		if(fechaModificacion != null && fechaModificacion.compareTo(dateMonthAgo) >= 0)
			return arial10Red;
		
		return arial10;
	}

	private static String formatDate(Date date) {
		if (date != null) {
			return cellDateFormat.format(date);
		}
		return EMPTY;
	}	
}