package com.loris.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.loris.bean.Paginator;
import com.loris.dao.ArticuloDAO;
import com.loris.dao.FamiliaDAO;
import com.loris.dao.MarcaDAO;
import com.loris.domain.Articulo;
import com.loris.domain.Familia;
import com.loris.domain.Marca;
import com.loris.utils.DateUtils;
import com.loris.utils.SuccessUtils;
import com.loris.validator.ArticuloEditValidator;
import com.loris.validator.ArticuloValidator;
import com.loris.validator.PrecioValidator;

@Controller
@SessionAttributes(value="articulo")
public class ArticuloController {
	private static final String ARTICULO_TITLE = "Artículo";
	private static final String ARTICULO_NEW_MESSAGE = "Artículo agregado exitosamente";
	private static final String ARTICULO_MODIF_MESSAGE = "Artículo modificado exitosamente";
	private static final String ARTICULO_DEL_MESSAGE = "Artículo eliminado exitosamente";	
	@Autowired
	private ArticuloDAO articuloDAO;
	@Autowired
	private FamiliaDAO familiaDAO;
	@Autowired
	private MarcaDAO marcaDAO;
	@Autowired
	private ArticuloValidator articuloValidator;	
	@Autowired
	private ArticuloEditValidator articuloEditValidator;	
	@Autowired
	private PrecioValidator precioValidator;
	@Autowired
	private Paginator paginator;
	
	private final BigDecimal PORCENTUAL = new BigDecimal(100);
	
	@RequestMapping(value="/articulo.htm")
	public String showArticuloLinks(){
		return "/articulo/articuloLinks";
	}
	
	@RequestMapping(value="/articulo/create.htm", method = RequestMethod.GET)
	public String showArticuloForm(ModelMap modelMap){
		setModelMap(modelMap, new Articulo());
		
		return "/articulo/articuloForm";
	}
	
	@RequestMapping(value="/articulo/create.htm", method = RequestMethod.POST)
	public ModelAndView saveArticulo(@ModelAttribute("articulo") Articulo articulo, BindingResult result){
		articuloValidator.validate(articulo, result);
		if(result.hasErrors()){
			ModelMap modelMap = new ModelMap();
			setModelMap(modelMap, articulo);			
			return new ModelAndView("/articulo/articuloForm", modelMap);
		}
		articulo.setFamilia(familiaDAO.getFamilia(articulo.getFamiliaId()));
		articulo.setMarca(marcaDAO.getMarca(articulo.getMarcaId()));
		articulo.setActivo(Articulo.ACTIVE_MARK_YES);
		articuloDAO.saveUpdateArticulo(articulo);
		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(ARTICULO_TITLE, ARTICULO_NEW_MESSAGE));
	}	

	@RequestMapping(value="/articulo/edit.htm", method = RequestMethod.GET)
	public String showArticuloEditForm(ModelMap modelMap){
		modelMap.addAttribute("articulo", new Articulo());
		modelMap.addAttribute("marcas", marcaDAO.getMarcas());
		
		return "/articulo/articuloEditForm";
	}
	
	@RequestMapping(value="/articulo/edit.htm", method = RequestMethod.POST)
	public ModelAndView updateArticulo(@ModelAttribute("articulo") Articulo articulo, BindingResult result){
		articuloEditValidator.validate(articulo, result);
		
		if(result.hasErrors()){
			ModelMap modelMap = new ModelMap();
			modelMap.addAttribute("articulo", articulo);
			modelMap.addAttribute("marcas", marcaDAO.getMarcas());
			return new ModelAndView("/articulo/articuloEditForm", modelMap);
		}
		Articulo articuloDB = articuloDAO.getArticulo(articulo, Articulo.ACTIVE_MARK_YES);
		articulo.setId(articuloDB.getId());
		articulo.setMarca(articuloDB.getMarca());
		articulo.setFamilia(articuloDB.getFamilia());
		articulo.setActivo(Articulo.ACTIVE_MARK_YES);
		
		articuloDAO.saveUpdateArticulo(articulo);
		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(ARTICULO_TITLE, ARTICULO_MODIF_MESSAGE));
	}
	
	@RequestMapping(value="/articulo/delete.htm", method = RequestMethod.GET)
	public String showArticuloDeleteForm(ModelMap modelMap){
		modelMap.addAttribute("articulo", new Articulo());
		modelMap.addAttribute("marcas", marcaDAO.getMarcas());
		
		return "/articulo/articuloDeleteForm";
	}
	
	@RequestMapping(value="/articulo/delete.htm", method = RequestMethod.POST)
	public ModelAndView deleteArticulo(@ModelAttribute("articulo") Articulo articulo){
		Articulo articuloDB = articuloDAO.getArticulo(articulo, Articulo.ACTIVE_MARK_YES);
		
		if(articuloDB.getStock().compareTo(new BigDecimal(0)) > 0){
			return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean("Error", "Articulo con stock existente"));
		}
		
		articuloDB.setActivo(Articulo.ACTIVE_MARK_NO);
		articuloDAO.saveUpdateArticulo(articuloDB);
		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(ARTICULO_TITLE, ARTICULO_DEL_MESSAGE));
	}
	
	@RequestMapping(value="/articulo/modificacionPrecios.htm")
	public String showModificacionPreciosLinks(){
		return "/articulo/modificacionPreciosLinks";
	}
	
	@RequestMapping(value="/articulo/modifVenta.htm", method = RequestMethod.GET)
	public String showUpdatePreciosVenta(ModelMap model){
		model.addAttribute("marcas", marcaDAO.getMarcas());
		model.addAttribute("articulo", new Articulo());
		
		return "/articulo/modifVentaForm";
	}
	
	@RequestMapping(value="/articulo/modifVenta.htm", method = RequestMethod.POST)
	public ModelAndView updatePreciosVenta(@ModelAttribute(value="articulo")Articulo articulo, BindingResult result){
		precioValidator.validate(articulo, result);
		
		if(result.hasErrors()){
			ModelMap model = new ModelMap();
			model.addAttribute("marcas", marcaDAO.getMarcas());
			model.addAttribute("articulo", new Articulo());
			
			return new ModelAndView("/articulo/modifVentaForm", model);
		}
		
		List <Articulo> articulos = articuloDAO.getFilteredArticulos(articulo.getMarcaId(), articulo.getFamiliaIdForSearch(), articulo.getCodigo());
		
		if(articulos != null){
			BigDecimal porc = articulo.getPorcentajeModif().add(PORCENTUAL).divide(PORCENTUAL);
			
			for(Articulo articuloDB : articulos){
				articuloDB.setPrecioVenta(articuloDB.getPrecioVenta().multiply(porc));
				articuloDB.setFechaModifVenta(articulo.getFechaModifVenta());
				articuloDAO.saveUpdateArticulo(articuloDB);
			}
		}
		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean("Modificación de Precios", "Precios Modificados correctamente"));
	}
	
	@RequestMapping(value="/articulo/modifCosto.htm", method = RequestMethod.GET)
	public String showUpdatePreciosCosto(ModelMap model){
		model.addAttribute("marcas", marcaDAO.getMarcas());
		model.addAttribute("articulo", new Articulo());
		
		return "/articulo/modifCostoForm";
	}
	
	@RequestMapping(value="/articulo/modifCosto.htm", method = RequestMethod.POST)
	public ModelAndView updatePreciosCosto(@ModelAttribute(value="articulo")Articulo articulo, BindingResult result){
		precioValidator.validate(articulo, result);
		
		if(result.hasErrors()){
			ModelMap model = new ModelMap();
			model.addAttribute("marcas", marcaDAO.getMarcas());
			model.addAttribute("articulo", new Articulo());
			
			return new ModelAndView("/articulo/modifCostoForm", model);
		}
		
		List <Articulo> articulos = articuloDAO.getFilteredArticulos(articulo.getMarcaId(), articulo.getFamiliaIdForSearch(), articulo.getCodigo());
		
		if(articulos != null){
			BigDecimal porc = articulo.getPorcentajeModif().add(PORCENTUAL).divide(PORCENTUAL);
			
			for(Articulo articuloDB : articulos){
				articuloDB.setFechaModificacion(articulo.getFechaModificacion());
				articuloDB.setPrecioCosto(articuloDB.getPrecioCosto().multiply(porc));
				articuloDAO.saveUpdateArticulo(articuloDB);
			}
		}
		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean("Modificación de Precios", "Precios Modificados correctamente"));
	}
	
	@RequestMapping(value="/articulo/modifVentaTotal.htm", method = RequestMethod.GET)
	public String showUpdatePreciosVentaTotal(ModelMap model){		
		model.addAttribute("articulo", new Articulo());
		
		return "/articulo/modifVentaTotalForm";
	}
	
	@RequestMapping(value="/articulo/modifVentaTotal.htm", method = RequestMethod.POST)
	public ModelAndView updatePreciosVentaTotal(@ModelAttribute(value="articulo")Articulo articulo, BindingResult result){
		precioValidator.validate(articulo, result);
		
		if(result.hasErrors()){
			ModelMap model = new ModelMap();			
			model.addAttribute("articulo", new Articulo());
			
			return new ModelAndView("/articulo/modifVentaTotalForm", model);
		}
		
		List <Articulo> articulos = articuloDAO.getArticulosByMarca();
		
		if(articulos != null){
			BigDecimal porc = articulo.getPorcentajeModif().add(PORCENTUAL).divide(PORCENTUAL);
			
			for(Articulo articuloDB : articulos){
				articuloDB.setFechaModifVenta(articulo.getFechaModifVenta());
				articuloDB.setPrecioVenta(articuloDB.getPrecioVenta().multiply(porc));
				articuloDAO.saveUpdateArticulo(articuloDB);
			}
		}
		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean("Modificación de Precios", "Precios Modificados correctamente"));
	}
	
	@RequestMapping(value="/articulo/GetFamiliaCombo.json", method = RequestMethod.GET)
	public ModelAndView showFamiliaCombo(@RequestParam("marca") Integer marca){		
		final ModelAndView modelAndView = new ModelAndView();
		
		if(marca != null){
			List <Familia> familias = articuloDAO.getFamiliasByMarca(marca);
			if(familias != null && !familias.isEmpty()){
				modelAndView.addObject("success", Boolean.TRUE);
				modelAndView.addObject("familias", familias);
				return modelAndView;
			}			
		}		
		
		return modelAndView.addObject("success", Boolean.FALSE);
	}
	
	@RequestMapping(value="/articulo/GetCodigosCombo.json", method = RequestMethod.GET)
	public ModelAndView showCodigoCombo(@RequestParam("marca") Integer marca, @RequestParam("familia") Integer familia){		
		final ModelAndView modelAndView = new ModelAndView();
		
		if(marca != null && familia != null){
			List <Articulo> articulos = articuloDAO.getCodigosByMarcaAndFamilia(marca, familia);
			if(articulos != null && !articulos.isEmpty()){
				modelAndView.addObject("success", Boolean.TRUE);
				modelAndView.addObject("articulos", articulos);
				return modelAndView;
			}			
		}		
		
		return modelAndView.addObject("success", Boolean.FALSE);
	}
	
	
	@RequestMapping(value="/articulo/GetArticulo.json", method = RequestMethod.GET)
	public ModelAndView showArticuloFields(@RequestParam("marca") Integer marca, 
			@RequestParam("familia") Integer familia, @RequestParam("codigo") String codigo){
		final ModelAndView modelAndView = new ModelAndView();
		
		if(marca != null && familia != null && StringUtils.isNotBlank(codigo)){
			Articulo articulo = new Articulo();
			articulo.setCodigo(codigo);
			articulo.setFamilia(new Familia(familia));
			articulo.setMarca(new Marca(marca));
			
			articulo = articuloDAO.getArticulo(articulo, Articulo.ACTIVE_MARK_YES);
			if(articulo != null){
				modelAndView.addObject("success", Boolean.TRUE);
				modelAndView.addObject("articulo", articulo);
				modelAndView.addObject("fechaString", DateUtils.convertDateToString(articulo.getFechaModificacion()));
				modelAndView.addObject("fechaVentaString", DateUtils.convertDateToString(articulo.getFechaModifVenta()));
				return modelAndView;
			}			
		}		
		
		return modelAndView.addObject("success", Boolean.FALSE);
	}
	
	private void setModelMap(ModelMap modelMap, Articulo articulo) {
		modelMap.addAttribute("articulo", articulo);
		modelMap.addAttribute("marcas", marcaDAO.getMarcas());
		modelMap.addAttribute("familias", familiaDAO.getFamilias());		
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));        
    }
	
	@RequestMapping(value="/articulo/search.htm")
	public String showSearchForm(ModelMap modelMap){
		modelMap.addAttribute("articulo", new Articulo());
		modelMap.addAttribute("marcas", marcaDAO.getMarcas());
		
		return "/articulo/articuloSearchForm";
	}
	
	@RequestMapping(value="/articulo/paginator/init.htm")
	public ModelAndView showInitRecords(@ModelAttribute ("articulo") Articulo articuloParams){						
		paginator.setPageNumber(new Integer(1));
		paginator.setTotalItems(articuloDAO.getTotalItemsSearch(articuloParams.getMarcaId(), articuloParams.getFamiliaIdForSearch(), articuloParams.getCodigo()));
		List <Articulo> articulos = articuloDAO.getFilteredArticulos(paginator.getPageNumber(), 
			paginator.getRecordSize(), articuloParams.getMarcaId(), articuloParams.getFamiliaIdForSearch(), articuloParams.getCodigo());
		
		paginator.moreRecords();			
		
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("paginator", paginator);
		modelMap.addAttribute("articulos", articulos);
		modelMap.addAttribute("articulo", articuloParams);
		
		return new ModelAndView("/articulo/articuloList", modelMap);
	}
	
	@RequestMapping(value="/articulo/paginator/next.htm")
	public ModelAndView showNextRecords(@ModelAttribute ("articulo") Articulo articulo){				
		paginator.setPageNumber(paginator.getPageNumber() + 1);		
		List <Articulo> articulos = articuloDAO.getFilteredArticulos(paginator.getPageNumber(), 
				paginator.getRecordSize(), articulo.getMarcaId(), articulo.getFamiliaIdForSearch(), articulo.getCodigo());
		
		paginator.moreRecords();
		
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("paginator", paginator);
		modelMap.addAttribute("articulos", articulos);
		modelMap.addAttribute("articulo", articulo);
		
		return new ModelAndView("/articulo/articuloList", modelMap);
	}
	
	@RequestMapping(value="/articulo/paginator/previous.htm")
	public ModelAndView showPreviousRecords(@ModelAttribute ("articulo") Articulo articulo){				
		paginator.setPageNumber(paginator.getPageNumber() - 1);
				
		List <Articulo> articulos = articuloDAO.getFilteredArticulos(paginator.getPageNumber(), 
				paginator.getRecordSize(), articulo.getMarcaId(), articulo.getFamiliaIdForSearch(), articulo.getCodigo());
		
		paginator.moreRecords();
		
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("paginator", paginator);
		modelMap.addAttribute("articulos", articulos);
		modelMap.addAttribute("articulo", articulo);
		
		return new ModelAndView("/articulo/articuloList", modelMap);
	}
}