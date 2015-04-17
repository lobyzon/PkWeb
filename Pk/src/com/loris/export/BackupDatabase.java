package com.loris.export;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Date;

import com.loris.utils.DateUtils;


public class BackupDatabase {
	
	private static String dbName = "pk";
	private static String dbUser = "root";
	private static String dbPass = "CDJg2000";
	private static String MY_SQL_DUMP_LOCATION 	= "C:\\PkWeb\\DataBase\\bin\\";
	
	public static String BACKUP_DIRECTORY 		= "C:\\PkWeb\\Backup";	
	
	public static String backupDatabase(){					
		/***********************************************************/
		// Execute Shell Command
		/***********************************************************/
		//String executeCmd = "C:\\Archivos de Programa\\MySQL\\MySQL Server 5.1\\bin\\";
		String executeCmd 	= MY_SQL_DUMP_LOCATION;
		executeCmd 			+= 	"mysqldump --opt --user=" + dbUser + " --password=" + dbPass + " " + dbName;

		String fwName = BACKUP_DIRECTORY + "\\backup_" + 
						DateUtils.convertDateToStringSubstractSeparator(new Date()) + ".sql";
				
		try {
			Process child = Runtime.getRuntime().exec(executeCmd);
			FileWriter fw = new FileWriter(fwName);
			InputStreamReader irs = new InputStreamReader(child.getInputStream());
			BufferedReader br = new BufferedReader(irs);
			
			String line;
			while( (line=br.readLine()) != null ) {
				fw.write(line + "\n");
			}
			fw.close();
			irs.close();
			br.close();
			}catch(Exception e){
				e.printStackTrace();
				return "No se pudo realizar el Backup, error: " + e.getMessage();
			}
						
		return "Backup satisfactorio al " + DateUtils.convertDateToString(new Date());		
	}
	
	public static String restoreDatabase(String fileName){
		String fileURL = BACKUP_DIRECTORY + "\\" + fileName;
		try{			
			Runtime.getRuntime().exec(	"cmd /c mysql --password=" + dbPass + " --user=" 
										+ dbUser + " " + dbName + " <" + fileURL);
		}catch(Exception e){
			return "No se pudo realizar el restore de la base, error: " + e.getMessage();
		}
			return "Restore de la base realizado con éxito, archivo backup: " + fileName;		
	}
	
	public static void main(String[] args) {		
		System.out.println(BackupDatabase.restoreDatabase("backup_28-07-2011.sql"));
	}	
}