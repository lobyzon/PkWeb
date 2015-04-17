package com.loris.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.loris.domain.Factura;

public class ConsultaFacturaValidator implements Validator{	
	
	public boolean supports(Class<?> clazz) {
		return Factura.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nroFactura", MessageConstants.FIELD_REQUIRED);
				
		Factura facturaDB = (Factura) target;
		if(facturaDB == null)
			errors.rejectValue("nroFactura", MessageConstants.FACTURA_INEXISTENT);		
	}
}