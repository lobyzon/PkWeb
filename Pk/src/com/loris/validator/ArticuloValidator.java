package com.loris.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.loris.dao.ArticuloDAO;
import com.loris.domain.Articulo;

public class ArticuloValidator implements Validator{
	@Autowired
	ArticuloDAO articuloDAO;
	
	public boolean supports(Class<?> clazz) {
		return Articulo.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Articulo articulo = (Articulo) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigo", MessageConstants.ID_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, MessageConstants.DESCRIPTION, MessageConstants.DESCRIPTION_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familiaId", MessageConstants.FAMILIA_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "marcaId", MessageConstants.MARCA_REQUIRED);		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stockMinimo", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precioCosto", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precioVenta", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaModificacion", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaModifVenta", MessageConstants.FIELD_REQUIRED);
		
		if(articulo.getCodigo() != null && StringUtils.isNotBlank(articulo.getCodigo().toString())){
			if(articuloDAO.getArticulo(articulo, Articulo.ACTIVE_MARK_YES) != null)
				errors.rejectValue("codigo", MessageConstants.ID_EXISTENT);
		}
		
	}
}