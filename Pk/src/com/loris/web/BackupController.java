package com.loris.web;

import java.io.File;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.loris.export.BackupDatabase;
import com.loris.utils.SuccessUtils;

@Controller
public class BackupController {
	
	final String BACKUP_TITLE = "Copia de Seguridad";
	
	@RequestMapping(value="/backup.htm")
	public String showBackupPage(){
		return "/backup/backupPage";
	}
	
	@RequestMapping(value="/backup/backupDataBase.htm")
	public ModelAndView backupDataBase(){		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(BACKUP_TITLE, BackupDatabase.backupDatabase()));
	}
	
	@RequestMapping(value="/backup/restoreSelect.htm")
	public ModelAndView restoreSelect(){				
		ModelAndView model = new ModelAndView();
		
		File dir = new File(BackupDatabase.BACKUP_DIRECTORY);
		
		String [] fileNames = dir.list();
		if(fileNames != null && fileNames.length != 0){
			model.addObject("fileNames", Arrays.asList(fileNames));
		}else{
			model.addObject("error", "No existen archivos para restaurar la base de datos");
		}
		
		model.setViewName("/backup/restoreSelect");
		
		return model;
	}
	
	@RequestMapping(value="/backup/restoreDataBase.htm")
	public ModelAndView restoreDatabase(@RequestParam(value="fileName")String fileName){
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(BACKUP_TITLE, BackupDatabase.restoreDatabase(fileName)));
	}
}