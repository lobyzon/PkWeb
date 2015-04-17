package com.loris.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.loris.bean.CheckBoxes;

public class CheckBoxesValidator implements Validator{	
	
	public boolean supports(Class<?> clazz) {
		return CheckBoxes.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		CheckBoxes checkBoxes = (CheckBoxes) target;
		
		if(!(checkBoxes.getCheckBoxes().length > 0))
			errors.rejectValue("checkBoxes", MessageConstants.REQUIRED_OPTION);
	}
}