package com.mumscheduler.faculty.validator;

import com.mumscheduler.faculty.model.Faculty;
import com.mumscheduler.security.service.UserServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@PropertySource("classpath:i18n/validation/user.properties")
@Component
public class FacultyValidator implements Validator {

	@Autowired
	private Environment env;

	@Autowired
	private UserServiceInterface userService;
	
	@Override
	public boolean supports(Class<?> classToValidate) {
		return Faculty.class.equals(classToValidate);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Faculty faculty = (Faculty) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "firstname.notnull", env.getProperty("firstname.notnull"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "lastname.notnull", env.getProperty("lastname.notnull"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.notnull", env.getProperty("email.notnull"));
		
		/**
		 * When we save we check if the code is unique
		 */
		if( faculty.getId() == null && userService.findByEmail(faculty.getEmail()) != null) {
			errors.rejectValue("email", "email.unique", env.getProperty("email.unique"));
		}
	}
}
