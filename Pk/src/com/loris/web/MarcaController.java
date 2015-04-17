package com.loris.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.loris.dao.ArticuloDAO;
import com.loris.dao.MarcaDAO;
import com.loris.domain.Marca;
import com.loris.utils.SuccessUtils;
import com.loris.validator.MarcaEditValidator;
import com.loris.validator.MarcaValidator;

@Controller
public class MarcaController {
	private MarcaValidator marcaValidator;
	private MarcaEditValidator marcaEditValidator;
	@Autowired
	private ArticuloDAO articuloDAO;
	private MarcaDAO marcaDAO;
	private final String BRAND_TITLE = "Marca";
	private final String BRAND_CREATE_MESSAGE = "Marca agregada exitosamente";
	private final String BRAND_EDIT_MESSAGE = "Marca modificada exitosamente";
	private final String BRAND_DEL_MESSAGE = "Marca eliminada exitosamente";
	
	@Autowired
	public MarcaController(MarcaValidator marcaValidator, MarcaEditValidator marcaEditValidator, MarcaDAO marcaDAO){
		this.marcaValidator = marcaValidator;
		this.marcaEditValidator = marcaEditValidator;
		this.marcaDAO = marcaDAO;
	}
	
	@RequestMapping(value="/marca/create.htm", method = RequestMethod.GET)
	public String showBrandForm(ModelMap model) {		
		Marca marca = new Marca();
		model.addAttribute("marca", marca);
		model.addAttribute("marcas", marcaDAO.getMarcas());
		
		return "/marca/marcaForm";
	}
	
	@RequestMapping(value="/marca/create.htm", method = RequestMethod.POST)
	public ModelAndView onSubmit(@ModelAttribute("marca") Marca marca, BindingResult result) {
		marcaValidator.validate(marca, result);
		if (result.hasErrors()) {
			return new ModelAndView("/marca/marcaForm");
		} 
			
		marca.setActivo(Marca.MARCA_ACTIVA);
		marcaDAO.saveMarca(marca);
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(BRAND_TITLE, BRAND_CREATE_MESSAGE));		
	}	
	
	@RequestMapping(value="/marca/delete.htm")
	public ModelAndView deleteBrand(@RequestParam("id") Integer marcaId){
		if(!articuloDAO.getArticulosByMarca(marcaId).isEmpty())
			return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean("Error", "Artículos existentes con Marca: " + marcaId));
		
		Marca marcaDB = this.marcaDAO.getMarca(marcaId);
		marcaDB.setActivo(Marca.MARCA_NO_ACTIVA);
		marcaDAO.saveMarca(marcaDB);
		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(BRAND_TITLE, BRAND_DEL_MESSAGE));
	}
	
	@RequestMapping(value="/marca/edit.htm", method = RequestMethod.GET )
	public ModelAndView editBrand(@RequestParam("id") Integer brandId){		
		return new ModelAndView("/marca/marcaEdit", "marca", this.marcaDAO.getMarca(brandId));		
	}
	
	@RequestMapping(value="/marca/edit.htm", method = RequestMethod.POST)
	public ModelAndView onSubmitEdit(@ModelAttribute("marca") Marca marca, BindingResult result) {
		marcaEditValidator.validate(marca, result);
		if (result.hasErrors()) {
			return new ModelAndView("/marca/marcaEdit");
		} else {
			marca.setActivo(Marca.MARCA_ACTIVA);
			marcaDAO.saveMarca(marca);			
			return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(BRAND_TITLE, BRAND_EDIT_MESSAGE));
		}
	}
	
	@RequestMapping(value="/marca/GetMarcaDescripcion.json", method = RequestMethod.GET)
	public ModelAndView showMarcaDescription(@RequestParam("code") String codigo){		
		final ModelAndView modelAndView = new ModelAndView();
		
		if(StringUtils.isNotBlank(codigo)){
			Marca marca  = marcaDAO.getMarca(new Integer(codigo));			
			modelAndView.addObject("success", Boolean.TRUE);
			modelAndView.addObject("descripcion", marca.getDescripcion());
			
			return modelAndView;
		}		
		
		return modelAndView.addObject("success", Boolean.FALSE);
	}
}