package com.example.eazyschool.validations;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.example.eazyschool.annotation.PasswordValidator;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {
	
	List<String> weakPasswords;
	
	@Override
    public void initialize(PasswordValidator passwordValidator) {
        weakPasswords = Arrays.asList("12345", "password", "qwerty","Password","pswrd");
    }

	@Override
	public boolean isValid(String passwordField, ConstraintValidatorContext context) {
		
		return passwordField !=null && (!weakPasswords.contains(passwordField));
	}

}
