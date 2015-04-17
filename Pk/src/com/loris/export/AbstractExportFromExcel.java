package com.loris.export;

import jxl.Sheet;

import org.apache.commons.lang.StringUtils;

public abstract class AbstractExportFromExcel {
	
	protected String getMarca(String code) {		
		if(code.indexOf("-") > 0)
			return code.substring(0, code.indexOf("-"));
		else
			return code.substring(0, code.indexOf("."));
	}

	protected String getFamilia(String code) {
		System.out.println("CODE: " + code);
		if(code.indexOf("-") > 0)
			return code.substring(code.indexOf("-") + 1, code.lastIndexOf("-"));
		else
			return code.substring(code.indexOf(".") + 1, code.lastIndexOf("."));
	}
	
	protected String getCodigo(String code) {
		if(code.indexOf("-") > 0)
			return "'" + code.substring(code.lastIndexOf("-") + 1) + "'";
		else
			return "'" + code.substring(code.lastIndexOf(".") + 1) + "'";
	}

	protected String getPrecioVenta(Sheet sheet, int rowIndex) {		
		return sheet.getCell(2, rowIndex).getContents();
	}

	protected String getFechaModif(Sheet sheet, int rowIndex) {
		String fecha = sheet.getCell(3, rowIndex).getContents();
		String fechaDB = "2009-01-01";
		
		if(StringUtils.isNotBlank(fecha)){
			fechaDB = 	fecha.substring(fecha.lastIndexOf("/") + 1) + "-" + 
						fecha.substring(fecha.indexOf("/") + 1, fecha.lastIndexOf("/"))
						+ "-" + fecha.substring(0, fecha.indexOf("/")); 
		}
		
		return "'" + fechaDB  + "'";
	}

	protected String getDescripcion(Sheet sheet, int rowIndex) {
		return "'" + sheet.getCell(1, rowIndex).getContents() + "'";
	}	
}