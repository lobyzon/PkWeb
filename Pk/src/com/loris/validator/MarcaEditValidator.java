package com.loris.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.loris.domain.Marca;

public class MarcaEditValidator implements Validator{	

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, MessageConstants.DESCRIPTION, MessageConstants.DESCRIPTION_REQUIRED);		
	}

	public boolean supports(Class<?> clazz) {		
		return Marca.class.isAssignableFrom(clazz);
	}
}
