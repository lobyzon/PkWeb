package com.loris.web;

import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.loris.bean.CheckBoxes;
import com.loris.bean.Printer;
import com.loris.dao.ArticuloDAO;
import com.loris.dao.FamiliaDAO;
import com.loris.dao.MarcaDAO;
import com.loris.export.ListaExcelExport;
import com.loris.print.PrintListaByFamiliaBook;
import com.loris.print.PrintListaByMarcaBook;
import com.loris.utils.DateUtils;
import com.loris.utils.SuccessUtils;
import com.loris.validator.CheckBoxesValidator;

@Controller
@SessionAttributes(value={"familiaChecks", "marcaChecks", "printers", "printer"})
public class ListasController {
	@Autowired
	private ArticuloDAO articuloDAO;
	@Autowired
	private FamiliaDAO familiaDAO;
	@Autowired
	private MarcaDAO marcaDAO;
	@Autowired
	private CheckBoxesValidator checkBoxesValidator;
	
	@RequestMapping(value="/listas.htm")
	public String showListasLinks(ModelMap modelMap){
		return "/listas/listasLinks";
	}	
	
	@RequestMapping(value="/listas/listaPorFamilia.htm", method=RequestMethod.GET)
	public String printListaByFamiliaGET(ModelMap map){
		addPrinterSelectionData(map);
		map.addAttribute("action", "/controller/listas/listaPorFamilia.htm");
		
		return "/listas/printerSelection";		
	}
	
	@RequestMapping(value="/listas/listaPorFamilia.htm", method=RequestMethod.POST)
	public ModelAndView printListaByFamilia(ModelMap map, @RequestParam(value="printerServiceIndex")int printerServiceIndex){
		new PrintListaByFamiliaBook(articuloDAO.getArticulosByFamilia(), new Printer(printerServiceIndex));
			
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean("Listas", "Impresión de lista al " + " " 
			+ DateUtils.convertDateToString(new Date()) + " por Familia finalizada"));
	}
	
	@RequestMapping(value="/listas/listaParcialPorFamilia.htm", method = RequestMethod.GET)
	public String showFamiliasCheckBoxesPage(ModelMap map){
		map.addAttribute("familias", familiaDAO.getFamilias());
		map.addAttribute("familiaChecks", new CheckBoxes());
		addPrinterSelectionData(map);
		
		return "/listas/listaParcialPorFamiliaPage";
	}
	
	@RequestMapping(value="/listas/listaParcialPorFamilia.htm", method = RequestMethod.POST)
	public ModelAndView printListaParcialPorFamilia(@ModelAttribute(value="familiaChecks") CheckBoxes familiaCheks, BindingResult result,
			@RequestParam(value="printerServiceIndex") int printerServiceIndex){
		checkBoxesValidator.validate(familiaCheks, result);
		
		if(result.hasErrors()){
			ModelMap modelMap = new ModelMap();
			modelMap.addAttribute("familias", familiaDAO.getFamilias());
			return new ModelAndView("/listas/listaParcialPorFamiliaPage", modelMap);
		}
		
		new PrintListaByFamiliaBook(articuloDAO.getArticulosByFamiliaParcial(familiaCheks), new Printer(printerServiceIndex));
		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean("Listas", "Impresión de lista parcial por Familia al " + " " 
				+ DateUtils.convertDateToString(new Date()) + " finalizada"));
	}
	
	@RequestMapping(value="/listas/listaPorMarca.htm", method=RequestMethod.GET)
	public String printListaByMarcaGET(ModelMap map){
		addPrinterSelectionData(map);
		map.addAttribute("action", "/controller/listas/listaPorMarca.htm");
		
		return "/listas/printerSelection";		
	}
	
	@RequestMapping(value="/listas/listaPorMarca.htm", method=RequestMethod.POST)
	public ModelAndView printListaByMarca(ModelMap map, @RequestParam(value="printerServiceIndex")int printerServiceIndex){				
		new PrintListaByMarcaBook(articuloDAO.getArticulosByMarca(), new Printer(printerServiceIndex));
			
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean("Listas", "Impresión de lista al " + " " 
			+ DateUtils.convertDateToString(new Date()) + " por Marca finalizada"));
	}
	
	@RequestMapping(value="/listas/listaParcialPorMarca.htm", method = RequestMethod.GET)
	public String showMarcasCheckBoxesPage(ModelMap map){
		map.addAttribute("marcas", marcaDAO.getMarcas());
		map.addAttribute("marcaChecks", new CheckBoxes());
		addPrinterSelectionData(map);
		
		return "/listas/listaParcialPorMarcaPage";
	}
	
	@RequestMapping(value="/listas/listaParcialPorMarca.htm", method = RequestMethod.POST)
	public ModelAndView printListaParcialPorMarca(@ModelAttribute(value="marcaChecks") CheckBoxes marcaCheks, BindingResult result,
			@RequestParam(value="printerServiceIndex") int printerServiceIndex){
		checkBoxesValidator.validate(marcaCheks, result);
		
		if(result.hasErrors()){
			ModelMap modelMap = new ModelMap();
			modelMap.addAttribute("marcas", marcaDAO.getMarcas());
			return new ModelAndView("/listas/listaParcialPorMarcaPage", modelMap);
		}
		
		new PrintListaByMarcaBook(articuloDAO.getArticulosByMarcaParcial(marcaCheks), new Printer(printerServiceIndex));
		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean("Listas", "Impresión de lista parcial por Marca al " + " " 
				+ DateUtils.convertDateToString(new Date()) + " finalizada"));
	}
		
	@RequestMapping(value="/listas/listaExcel.htm")
	public ModelAndView makeListaExcel(HttpServletRequest request, HttpServletResponse response){		
		//final byte[] outputFileContent = ListaExcelExport.generateFileOutputContent(articuloDAO.getArticulosByFamilia(), "c:\\Documents and Settings\\43354556\\Desktop\\PK\\ListaLoris.xls");
		final byte[] outputFileContent = ListaExcelExport.generateFileOutputContent(articuloDAO.getArticulosByFamilia(), "c:\\PkWeb\\Lista\\ListaLoris.xls");		
		
		if (outputFileContent != null) {
			try {
				response.setHeader("Content-Disposition", "attachment;filename=" + "Lista Loris.xls");
				response.getOutputStream().write(outputFileContent);
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException e) {
				return new ModelAndView("/listas/listasLinks");
			}
		}
		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean("Listas", "Generación de Lista Excel al " + " " 
				+ DateUtils.convertDateToString(new Date()) + " finalizada"));
	}
	
	private void addPrinterSelectionData(ModelMap map){
		map.addAttribute("printers", PrinterJob.lookupPrintServices());
		map.addAttribute("printer", new Printer());
	}
}