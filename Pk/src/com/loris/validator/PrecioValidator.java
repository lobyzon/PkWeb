package com.loris.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.loris.domain.Articulo;

public class PrecioValidator implements Validator{
	
	public boolean supports(Class<?> clazz) {
		return Articulo.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "porcentajeModif", MessageConstants.FIELD_REQUIRED);
	}
}