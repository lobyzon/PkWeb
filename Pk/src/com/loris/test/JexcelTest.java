package com.loris.test;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class JexcelTest {
	
	public void generateExcel(){
		WritableWorkbook workbook = null;
		WorkbookSettings ws = new WorkbookSettings();
	    ws.setLocale(new Locale("es", "ES"));
	    ws.setSuppressWarnings(true);
	    
	    try {
			workbook = Workbook.createWorkbook(new File("c:\\Documents and Settings\\43354556\\Desktop\\ExcelTest.xls"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		WritableSheet sheet = workbook.createSheet("Test", 0);
		WritableImage imgobj = new WritableImage(5, 7, 9, 11, new File("c:\\Documents and Settings\\43354556\\Desktop\\Argentina.png"));
	    sheet.addImage(imgobj);
		
	}
	
	public static void main(String[] args) {
		JexcelTest test = new JexcelTest();
		test.generateExcel();
		System.out.println("Listo");
	}
}
