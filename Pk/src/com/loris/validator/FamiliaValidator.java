package com.loris.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.loris.dao.FamiliaDAO;
import com.loris.domain.Familia;

public class FamiliaValidator implements Validator{
	
	@Autowired
	private FamiliaDAO familiaDAO;

	
	public boolean supports(Class<?> clazz) {
		return Familia.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigo", MessageConstants.ID_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, MessageConstants.DESCRIPTION, MessageConstants.DESCRIPTION_REQUIRED);
		
		Familia familia = (Familia) target;
		if(familia.getCodigo() != null && StringUtils.isNotBlank(familia.getCodigo().toString())){
			if(familiaDAO.getFamilia(familia.getCodigo()) != null)
				errors.rejectValue("codigo", MessageConstants.ID_EXISTENT);
		}
	}
}