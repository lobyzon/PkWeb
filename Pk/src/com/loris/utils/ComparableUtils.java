package com.loris.utils;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.loris.domain.Articulo;

public class ComparableUtils {	
       
	public static String getCodigo(Articulo articulo) {
        String result = "";
        result += articulo.getFamilia().getCodigo().toString() + articulo.getCodigo();
       
        for(int i = result.length(); i < 10; i++){
        	result += "0";
        }
       
        return result;
	} 
	
	public static BigDecimal getComparableCodigoArticulo(String codArticulo){
		String codNumeric = "";
		boolean fin = false;
		int i = 0;
		
		for(int j = 0; j < codArticulo.length(); j++){
			String charAct = "";
			if(j + 1 < codArticulo.length())
				charAct = codArticulo.substring(j, j + 1);
			else
				charAct = codArticulo.substring(j);
			
			if(StringUtils.isNumeric(charAct)){
				codNumeric += charAct;				
			}
		}
		
		for( ; i < codNumeric.length() && !fin; i++){
			if(codNumeric.substring(i, i + 1).equals("0")){
				i++;
			}else
				fin = true;
		}
		
		BigDecimal result = null;
		BigDecimal codArticuloDecimal = new BigDecimal(codNumeric);
		switch(i){
			case 1: result = codArticuloDecimal.divide(new BigDecimal(10));break;
			case 2: result = codArticuloDecimal.divide(new BigDecimal(100));break;
			case 3: result = codArticuloDecimal.divide(new BigDecimal(1000));break;
			case 4: result = codArticuloDecimal.divide(new BigDecimal(10000));break;
			case 5: result = codArticuloDecimal.divide(new BigDecimal(100000));break;
			case 6: result = codArticuloDecimal.divide(new BigDecimal(1000000));break;
			case 7: result = codArticuloDecimal.divide(new BigDecimal(10000000));break;
			default: result = codArticuloDecimal;break;
			
		}
		return result;		
	}
	
	public static void main(String[] args) {
		String codNumeric = "";
		String codArticulo = "00050";		
		int i = 0;
		boolean fin = false;
		
		for(int j = 0; j < codArticulo.length(); j++){
			String charAct = "";
			if(j + 1 < codArticulo.length())
				charAct = codArticulo.substring(j, j + 1);
			else
				charAct = codArticulo.substring(j);
			
			if(StringUtils.isNumeric(charAct)){
				codNumeric += charAct;
				if(charAct.equals("0") && !fin){
					i++;
				}
			}
		}
		
		System.out.println("CODIGO: " + codNumeric + " I " + i);
	}
}
