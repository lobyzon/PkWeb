package com.loris.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.loris.dao.ParamsDAO;
import com.loris.domain.Params;
import com.loris.utils.SuccessUtils;
import com.loris.validator.ParamsValidator;

@Controller
@SessionAttributes(value="params")
public class ParametrosController {
	@Autowired
	private ParamsDAO paramsDAO;	
	@Autowired
	private ParamsValidator paramsValidator;
	
	@RequestMapping(value="/factura/parametros.htm", method = RequestMethod.GET)
	public String showParamsForm(ModelMap modelMap){
		modelMap.addAttribute("params", paramsDAO.getParams());		
		
		return "/factura/paramsForm";
	}
	
	@RequestMapping(value="/factura/parametros.htm", method = RequestMethod.POST)
	public ModelAndView saveParams(@ModelAttribute("params")Params params, BindingResult result){
		paramsValidator.validate(params, result);
		if(result.hasErrors()){
			return new ModelAndView("/factura/paramsForm");
		}
		
		paramsDAO.saveOrUpdateParams(params);
		
		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean("Parámetros", "Parámetros grabados exitosamente"));
	}
}
