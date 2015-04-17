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
import com.loris.dao.ClienteDAO;
import com.loris.dao.ProvinciaDAO;
import com.loris.domain.Cliente;
import com.loris.utils.SuccessUtils;
import com.loris.validator.ClienteEditValidator;
import com.loris.validator.ClienteValidator;

@Controller
@SessionAttributes(value={"clientes", "cliente"})
public class ClienteController {
	final String CLIENTE_TITLE = "Cliente";
	final String CLIENTE_NEW_MESSAGE = "Cliente agregado exitosamente";
	final String CLIENTE_MODIF_MESSAGE = "Cliente modificado exitosamente";
	final String CLIENTE_DEL_MESSAGE = "Cliente eliminado exitosamente";
	@Autowired
	private ClienteDAO clienteDAO;
	@Autowired
	private ProvinciaDAO provinciaDAO;
	@Autowired
	private ClienteValidator clienteValidator;
	@Autowired
	private ClienteEditValidator clienteEditValidator;
	@Autowired
	private Paginator paginator;
	
	@RequestMapping(value="/clientes.htm")
	public String showClienteLinks(){
		return "/cliente/clienteLinks";
	}
	
	@RequestMapping(value="/cliente/create.htm", method = RequestMethod.GET)
	public String showClienteForm(ModelMap modelMap){
		setModelMap(modelMap, new Cliente());
		
		return "/cliente/clienteForm";
	}
	
	@RequestMapping(value="/cliente/create.htm", method = RequestMethod.POST)
	public ModelAndView saveCliente(@ModelAttribute ("cliente") Cliente cliente, BindingResult result){
		clienteValidator.validate(cliente, result);
		if(result.hasErrors()){
			ModelMap modelMap = new ModelMap();
			setModelMap(modelMap, cliente);
			
			return new ModelAndView("/cliente/clienteForm", modelMap);
		}
		
		clienteDAO.saveUpdateCliente(cliente);
		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(CLIENTE_TITLE, CLIENTE_NEW_MESSAGE));
	}
	
	@RequestMapping(value="/cliente/edit.htm", method = RequestMethod.GET)
	public String showClienteEditForm(ModelMap modelMap){
		setModelMap(modelMap, new Cliente());
		modelMap.addAttribute("clientes", clienteDAO.getClientes());
		
		return "/cliente/clienteEditForm";
	}
	
	@RequestMapping(value="/cliente/edit.htm", method = RequestMethod.POST)
	public ModelAndView updateCliente(@ModelAttribute ("cliente") Cliente cliente, BindingResult result){
		clienteEditValidator.validate(cliente, result);
		if(result.hasErrors()){
			ModelMap modelMap = new ModelMap();
			modelMap.addAttribute("cliente", cliente);
			modelMap.addAttribute("provincias", provinciaDAO.getProvincias());
			modelMap.addAttribute("clientes", clienteDAO.getClientes());
			return new ModelAndView("/cliente/clienteForm", modelMap);
		}
		
		clienteDAO.saveUpdateCliente(cliente);
		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(CLIENTE_TITLE, CLIENTE_MODIF_MESSAGE));
	}
	
	@RequestMapping(value="/cliente/GetClienteFields.json", method = RequestMethod.GET)
	public ModelAndView showFamiliaEdit(@RequestParam("codigo") String codigo){		
		final ModelAndView modelAndView = new ModelAndView();
		
		if(StringUtils.isNotBlank(codigo)){
			Cliente cliente  = clienteDAO.getCliente(new Integer(codigo));			
			modelAndView.addObject("success", Boolean.TRUE);
			modelAndView.addObject("cliente", cliente);
			modelAndView.addObject("direccion", cliente.getDireccion());
			modelAndView.addObject("provincia", cliente.getDireccion().getProvincia());
			
			return modelAndView;
		}		
		
		return modelAndView.addObject("success", Boolean.FALSE);
	}
	
	@RequestMapping(value="/cliente/delete.htm", method=RequestMethod.GET)
	public String showDeleteClienteForm(ModelMap modelMap){
		setModelMap(modelMap, new Cliente());
		modelMap.addAttribute("clientes", clienteDAO.getClientes());
		
		return "/cliente/clienteDeleteForm";
	}
	
	@RequestMapping(value="/cliente/delete.htm", method=RequestMethod.POST)
	public ModelAndView deleteCliente(@ModelAttribute (value = "cliente") Cliente cliente){		
		clienteDAO.deleteCliente(clienteDAO.getCliente(cliente.getId()));
		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(CLIENTE_TITLE, CLIENTE_DEL_MESSAGE));
	}
	
	@RequestMapping(value="/cliente/search.htm")
	public ModelAndView showSearchForm(ModelMap modelMap){
		List <Cliente> clientesTotal = clienteDAO.getClientes();
		
		paginator.setPageNumber(new Integer(1));
		paginator.setTotalItems(clientesTotal.size());		
		
		List <Cliente> clientesPage = clienteDAO.getClienteRecordsPage(paginator.getPageNumber(), paginator.getRecordSize());
		
		paginator.moreRecords();
				
		modelMap.addAttribute("paginator", paginator);
		modelMap.addAttribute("clientes", clientesTotal);
		modelMap.addAttribute("clientesPage", clientesPage);
		modelMap.addAttribute("cliente", new Cliente());
		
		return new ModelAndView("/cliente/clienteSearchForm", modelMap);
	}
	
	@RequestMapping(value="/cliente/paginator/next.htm")
	public ModelAndView showNextRecords(ModelMap modelMap){				
		paginator.setPageNumber(paginator.getPageNumber() + 1);
		List <Cliente> clientesPage = clienteDAO.getClienteRecordsPage(paginator.getPageNumber(), paginator.getRecordSize());
		
		paginator.moreRecords();
		
		modelMap.addAttribute("paginator", paginator);		
		modelMap.addAttribute("clientesPage", clientesPage);
		
		return new ModelAndView("/cliente/clienteSearchForm", modelMap);
	}
	
	@RequestMapping(value="/cliente/paginator/previous.htm")
	public ModelAndView showPreviousRecords(ModelMap modelMap){				
		paginator.setPageNumber(paginator.getPageNumber() - 1);
		
		List <Cliente> clientesPage = clienteDAO.getClienteRecordsPage(paginator.getPageNumber(), paginator.getRecordSize());
		
		paginator.moreRecords();
		
		modelMap.addAttribute("paginator", paginator);		
		modelMap.addAttribute("clientesPage", clientesPage);
		
		return new ModelAndView("/cliente/clienteSearchForm", modelMap);
	}
	
	private void setModelMap(ModelMap modelMap, Cliente cliente){
		modelMap.addAttribute("cliente", cliente);
		modelMap.addAttribute("provincias", provinciaDAO.getProvincias());
	}
}
