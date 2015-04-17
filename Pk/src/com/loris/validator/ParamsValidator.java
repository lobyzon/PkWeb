package com.loris.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.loris.domain.Params;

public class ParamsValidator implements Validator{
		
	public boolean supports(Class<?> clazz) {
		return Params.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cotizacionDolar", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matrixPrinterName", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "proxNumFactura", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cantCopiasFactura", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "proxNumRemito", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cantCopiasRemito", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "proxNumNC", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cantCopiasNC", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ivaInscripto", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ivaNoInscripto", MessageConstants.FIELD_REQUIRED);
		//N params
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "proxNumFacturaN", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cantCopiasFacturaN", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "proxNumFacturaD", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cantCopiasFacturaD", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "proxNumRemitoD", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cantCopiasRemitoD", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "proxNumNCD", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cantCopiasNCD", MessageConstants.FIELD_REQUIRED);
	}
}