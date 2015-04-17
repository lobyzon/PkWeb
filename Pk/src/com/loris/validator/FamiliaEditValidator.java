package com.loris.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.loris.domain.Familia;

public class FamiliaEditValidator implements Validator{	

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, MessageConstants.DESCRIPTION, MessageConstants.DESCRIPTION_REQUIRED);		
	}

	public boolean supports(Class<?> clazz) {		
		return Familia.class.isAssignableFrom(clazz);
	}

}
