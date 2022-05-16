package org.paymentology.validators;

import org.paymentology.models.Inputs;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class InputsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Inputs.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "file1", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "file2", "field.required");
		
		Inputs inputs = (Inputs) target;
		
		if(inputs.getFile1().isEmpty())
			errors.rejectValue("file1", "field.empty");
		
		if(inputs.getFile2().isEmpty())
			errors.rejectValue("file2", "field.empty");
		
		if(!inputs.getFile1().getContentType().contains("csv") )
			errors.rejectValue("file1", "field.extension");
		
		if(!inputs.getFile2().getContentType().contains("csv") )
			errors.rejectValue("file2", "field.extension");
	}


}
