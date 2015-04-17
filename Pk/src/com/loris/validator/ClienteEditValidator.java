package com.loris.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.loris.dao.ClienteDAO;
import com.loris.domain.Cliente;

public class ClienteEditValidator implements Validator{
	@Autowired
	ClienteDAO clienteDAO;
	
	public boolean supports(Class<?> clazz) {
		return Cliente.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Cliente cliente = (Cliente) target;					
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", MessageConstants.ID_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "razonSocial", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "direccion.calle", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "direccion.numero", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "direccion.codPostal", MessageConstants.FIELD_REQUIRED);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "direccion.localidad", MessageConstants.FIELD_REQUIRED);
		
		CuitValidator.validateCuit(errors, cliente.getCuit());
		
		if(cliente.getId() != null){
			if(clienteDAO.getCliente(cliente.getId()) == null)
				errors.rejectValue("id", MessageConstants.ID_INEXISTENT);
		}		
	}
}