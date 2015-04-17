package com.loris.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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

import com.loris.bean.Paginator;
import com.loris.dao.ArticuloDAO;
import com.loris.dao.FamiliaDAO;
import com.loris.domain.Familia;
import com.loris.utils.SuccessUtils;
import com.loris.validator.FamiliaEditValidator;
import com.loris.validator.FamiliaValidator;



@Controller
@SessionAttributes(value={"familia", "allFamilias"})
public class FamiliaController {	
	@Autowired
	private FamiliaDAO familiaDAO;
	@Autowired
	private ArticuloDAO articuloDAO;
	@Autowired
	private FamiliaValidator familiaValidator;
	@Autowired
	private FamiliaEditValidator familiaEditValidator;
	@Autowired
	private Paginator paginator;
	
	private final String FAMILIA_TITLE = "Familia";
	private final String FAMILIA_CREATE_MESSAGE = "Familia agregada exitosamente";
	private final String FAMILIA_EDIT_MESSAGE = "Familia modificada exitosamente";
	private final String FAMILIA_DEL_MESSAGE = "Familia eliminada exitosamente";
	
	@RequestMapping(value="/familia.htm")
	public String showLinkOptions(){
		return "/familia/familiaLinks";
	}
	
	@RequestMapping(value="/familia/create.htm", method = RequestMethod.GET)
	public String showFamiliaForm(ModelMap model) {		
		Familia familia = new Familia();
		model.addAttribute("familia", familia);		
		
		return "/familia/familiaForm";
	}
	
	@RequestMapping(value="/familia/create.htm", method = RequestMethod.POST)
	public ModelAndView onSubmit(@ModelAttribute("familia") Familia familia, BindingResult result) {
		familiaValidator.validate(familia, result);
		if (result.hasErrors()) {
			return new ModelAndView("/familia/familiaForm");
		} else {
			familia.setActivo(Familia.FAMILIA_ACTIVE);
			familiaDAO.saveFamilia(familia);			
			return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(FAMILIA_TITLE, FAMILIA_CREATE_MESSAGE));
		}
	}	
	
	@RequestMapping(value="/familia/delete.htm", method = RequestMethod.GET )
	public String showFamiliaDelete(ModelMap model) {		
		Familia familia = new Familia();
		model.addAttribute("familia", familia);		
		
		return "/familia/familiaDelete";
	}
	
	@RequestMapping(value="/familia/delete.htm", method = RequestMethod.POST)
	public ModelAndView deleteBrand(@ModelAttribute("familia") Familia familia){
		if(!articuloDAO.getArticulosByFamilia(familia.getCodigo()).isEmpty()){
			return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean("Error", "Articulos existentes con dicha familia"));
		}
		familia.setActivo(Familia.FAMILIA_NON_ACTIVE);
		this.familiaDAO.saveFamilia(familia);
		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(FAMILIA_TITLE, FAMILIA_DEL_MESSAGE));
	}
	
	@RequestMapping(value="/familia/edit.htm", method = RequestMethod.GET )
	public String showFamiliaEdit(ModelMap model) {		
		Familia familia = new Familia();
		model.addAttribute("familia", familia);
		
		return "/familia/familiaEdit";
	}
	
	@RequestMapping(value="/familia/edit.htm", method = RequestMethod.POST)
	public ModelAndView onSubmitEdit(@ModelAttribute("familia") Familia familia, BindingResult result) {
		familiaEditValidator.validate(familia, result);
		if (result.hasErrors()) {
			return new ModelAndView("/familia/familiaEdit");
		} else {
			familia.setActivo(Familia.FAMILIA_ACTIVE);
			familiaDAO.saveFamilia(familia);			
			return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(FAMILIA_TITLE, FAMILIA_EDIT_MESSAGE));
		}
	}
	
	@RequestMapping(value="/familia/GetFamiliaDescripcion.json")
	public ModelAndView showFamiliaEdit(@RequestParam("code") String codigo){		
		final ModelAndView modelAndView = new ModelAndView();
		
		if(StringUtils.isNotBlank(codigo)){
			Familia familia  = familiaDAO.getFamilia(new Integer(codigo));			
			modelAndView.addObject("success", Boolean.TRUE);
			modelAndView.addObject("descripcion", familia.getDescripcion());
			
			return modelAndView;
		}		
		
		return modelAndView.addObject("success", Boolean.FALSE);
	}			
	
	@RequestMapping(value="/familia/paginator/init.htm")
	public ModelAndView showInitRecords(ModelMap modelMap){				
		List<Familia> allFamilias = familiaDAO.getFamilias();
		
		paginator.setPageNumber(new Integer(1));
		paginator.setTotalItems(allFamilias.size());
		
		List <Familia> familias = familiaDAO.getFamiliaRecordsPage(paginator.getPageNumber(), paginator.getRecordSize());
		
		paginator.moreRecords();
		
		modelMap.addAttribute("paginator", paginator);
		modelMap.addAttribute("familias", familias);
		modelMap.addAttribute("allFamilias", allFamilias);
		
		return new ModelAndView("/familia/familiaList", modelMap);
	}
	
	@RequestMapping(value="/familia/paginator/next.htm")
	public ModelAndView showNextRecords(ModelMap modelMap){				
		paginator.setPageNumber(paginator.getPageNumber() + 1);
		List <Familia> familias = familiaDAO.getFamiliaRecordsPage(paginator.getPageNumber(), paginator.getRecordSize());
		
		paginator.moreRecords();
		
		modelMap.addAttribute("paginator", paginator);
		modelMap.addAttribute("familias", familias);
		
		return new ModelAndView("/familia/familiaList", modelMap);
	}
	
	@RequestMapping(value="/familia/paginator/previous.htm")
	public ModelAndView showPreviousRecords(ModelMap modelMap){				
		paginator.setPageNumber(paginator.getPageNumber() - 1);
		
		List <Familia> familias = familiaDAO.getFamiliaRecordsPage(paginator.getPageNumber(), paginator.getRecordSize());
		
		paginator.moreRecords();
		
		modelMap.addAttribute("paginator", paginator);
		modelMap.addAttribute("familias", familias);
		
		return new ModelAndView("/familia/familiaList", modelMap);
	}

}
