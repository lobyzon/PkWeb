package com.loris.export;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;

public class SQLArticulosGeneratorFromDBF_PC_AZUL{
	
	private final String dmlOperation;
	private final String fileOutputName;
	final static String DML_INSERT = "INSERT";	 
	final static String DML_UPDATE = "UPDATE";
	final static String DML_UPDATE_DESC_ART = "UPDATE_DESC_ART";
	final static String DML_UPDATE_FAMILIAS = "UPDATE_FAMILIAS";
	
	public SQLArticulosGeneratorFromDBF_PC_AZUL(String dmlOperationParam, String fileOutputName){
		this.dmlOperation = dmlOperationParam;
		this.fileOutputName = fileOutputName;
	}
	
	public void generateFileOutputContent() {
		FileReader fileReader = null;
		FileWriter fstream = null;
		BufferedWriter out = null;
		BufferedReader br = null; 
	    	    
		try {			
			fileReader = new FileReader("C:\\PkWeb\\Scripts\\ART_UPD.SQL");
			fstream = new FileWriter(this.fileOutputName);
	        out = new BufferedWriter(fstream);
	        br = new BufferedReader(fileReader);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		//Add rows from DB Articulos
		String dmlLine = "";
		
		try {
			String s = "";
			String [] line = new String[9];
			while( (s = br.readLine()) != null){ 				
			   System.out.println("LINEA: " + s);
			   //line = s.split(",\"");FOR Article Descriptions
			   line = s.split(",");
			   
			   if(DML_INSERT.equals(this.dmlOperation)){
				   dmlLine = makeInsertLine(line);
			   }
			   if(DML_UPDATE.equals(this.dmlOperation)){
				   dmlLine = makeUpdateLine(line);
			   }
			   if(DML_UPDATE_DESC_ART.equals(this.dmlOperation)){
				   dmlLine = makeUpdateDescArticulosLine(line);
			   }
			   if(DML_UPDATE_FAMILIAS.equals(this.dmlOperation)){
				   dmlLine = makeUpdateFamiliasLine(line);
			   }
									
			   out.write(dmlLine);
			   out.newLine();
			}
			out.flush();
			out.close();
			br.close();
			System.out.println("Generación de Script exitosa");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e2){
			e2.printStackTrace();
		}
	}
	
	private String makeUpdateLine(String [] line){
		return 	"UPDATE pk.articulo " +								
				"SET stock = " + getIntValue(line, 4) + ", stock_min = " + getIntValue(line, 3) 
				+ ", precio_costo = " + getStringValue(line, 5) + "\", precio_venta = "
				+ getStringValue(line, 7) + "\"" + getFechaModifUpdate(line, 6) + " WHERE codigo = " +
				getCodigo(line, 2) + " AND familia_id_fk = " + getIntValue(line, 1) 
				+ " AND marca_id_fk = " + getIntValue(line, 0) + ";";
	}
	
	private String makeUpdateDescArticulosLine(String [] line){
		return 	"UPDATE pk.articulo " +								
				"SET descripcion = " + getStringValue(line, 2) + " WHERE codigo = " +
				getCodigo(line, 1) + " AND familia_id_fk = " + getIntValue(line, 0) + ";";
	}
	
	private String makeUpdateFamiliasLine(String [] line){
		return 	"UPDATE pk.familia " +								
				"SET descripcion = " + getStringValue(line, 1) + " WHERE codigo_familia = " +
				getIntValue(line, 0) + ";";
	}
	
	private String makeInsertLine(String[] line) {
		return 	"INSERT INTO pk.articulo (stock, stock_min, precio_costo, precio_venta, fecha_modif, " +
				"codigo, familia_id_fk, marca_id_fk) values (" +								
				getIntValue(line, 4) + ", " + getIntValue(line, 3) +  
				", " + getStringValue(line, 5) + ", " + getStringValue(line, 7) + ", " + 
				getFechaParsed(line[6]) + ", " +	getCodigo(line, 2) + ", " + 
				getIntValue(line, 1) + ", " + getIntValue(line, 0) + ");";
	}

	private String getStringValue(String [] line, int posArray){
		String cadena = line[posArray];
		String cadenaAux = "\"";
		
		for(int i = 0; i < cadena.length(); i++){
			if('\"' == (cadena.charAt(i)) && (i < cadena.length() - 1))
				cadenaAux += "\\" + cadena.charAt(i); 
			else
				cadenaAux += cadena.charAt(i);
		}
		return cadenaAux;
	}
	
	private String getCodigo(String [] line, int posArray){
		return "'" + line[posArray].replace("\"", "") + "'";
	}
	
	private String getIntValue(String [] line, int posArray){
		return line[posArray];
	}
	
	private String getFechaModifUpdate(String [] line, int posArray){
		//Fecha 20100720 TO '2010-07-23'
		String fechaS = line[posArray];
		if(StringUtils.isNotBlank(fechaS) && fechaS.length() > 4)
			return 	", fecha_modif = " + getFechaParsed(fechaS) ;
		
		return "";
	}
	
	private String getFechaParsed(String fecha){
		if(StringUtils.isNotBlank(fecha))
			return "'" + fecha.substring(0, 4) + "-" + fecha.substring(4, 6) + "-" + fecha.substring(6) + "'";
		return "NULL";
	}
			
	public static void main(String[] args) {
		//SQLArticulosGeneratorFromDBF sqlInsert = new SQLArticulosGeneratorFromDBF(DML_INSERT, "c:\\PKWEB\\Scripts\\INSERTS_ARTICULOS_DBF.sql");
		SQLArticulosGeneratorFromDBF sqlUpdate = new SQLArticulosGeneratorFromDBF(DML_UPDATE, "c:\\PKWeb\\Scripts\\UPDATES_ARTICULOS_DBF.sql");
		//SQLArticulosGeneratorFromDBF sqlUpdateDescArt = new SQLArticulosGeneratorFromDBF(DML_UPDATE_DESC_ART, "C:\\Sergio\\Desarrollo\\Database\\Scripts\\UPDATES_ARTICULOS_DESC_DBF.sql");
		//SQLArticulosGeneratorFromDBF sqlUpdateFamilias = new SQLArticulosGeneratorFromDBF(DML_UPDATE_FAMILIAS, "C:\\Sergio\\Desarrollo\\Database\\Scripts\\UPDATES_FAMILIAS_DBF.sql");
		//sqlInsert.generateFileOutputContent();
		sqlUpdate.generateFileOutputContent();
		//sqlUpdateDescArt.generateFileOutputContent();
		//sqlUpdateFamilias.generateFileOutputContent();
	}
}