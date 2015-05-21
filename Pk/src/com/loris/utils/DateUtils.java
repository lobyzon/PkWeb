package com.loris.utils;

import java.text.*;
import java.util.Date;

public class DateUtils {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat sdfBackup = new SimpleDateFormat("dd-MM-yyyy");
	private static final SimpleDateFormat sdfCodigoBarras = new SimpleDateFormat("yyyyMMdd");
	
	public static boolean isValidDateStr(String date) {
		try {
			sdf.setLenient(false);
			sdf.parse(date);
		} catch (ParseException e) {
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	
	public static Date convertStringToDate(String date){
		sdf.setLenient(false);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {			
			e.printStackTrace();
			return null;
		}
	}
	
	public static String convertDateToString(Date date){
		if(date != null)
			return sdf.format(date);
		return "";
	}
	
	public static String convertDateToStringCodigoBarras(Date date){
		if(date != null)
			return sdfCodigoBarras.format(date);
		return "";
	}
	
	public static String convertDateToStringSubstractSeparator(Date date){
		if(date != null)
			return sdfBackup.format(date);
		return "";
	}
}
