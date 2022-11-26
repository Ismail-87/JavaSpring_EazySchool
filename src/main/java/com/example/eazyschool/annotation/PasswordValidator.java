package com.example.eazyschool.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.eazyschool.validations.PasswordStrengthValidator;

@Documented
@Constraint(validatedBy= {PasswordStrengthValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface PasswordValidator {
	String message() default "Please consider a strong password";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};

}
