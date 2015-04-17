package com.loris.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.loris.dao.MarcaDAO;
import com.loris.domain.Marca;

public class MarcaValidator implements Validator{
	
	@Autowired
	private MarcaDAO brandDAO;

	
	public boolean supports(Class<?> clazz) {
		return Marca.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, MessageConstants.ID, MessageConstants.ID_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, MessageConstants.DESCRIPTION, MessageConstants.DESCRIPTION_REQUIRED);
		
		Marca marca = (Marca) target;
		if(marca.getId() != null && StringUtils.isNotBlank(marca.getId().toString())){
			if(brandDAO.getMarca(marca.getId()) != null)
				errors.rejectValue(MessageConstants.ID, MessageConstants.ID_EXISTENT);
		}
	}
}