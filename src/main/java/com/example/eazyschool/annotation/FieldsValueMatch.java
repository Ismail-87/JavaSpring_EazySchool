package com.example.eazyschool.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.eazyschool.validations.FieldsValueMatchValidator;

@Documented
@Constraint(validatedBy= FieldsValueMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface FieldsValueMatch {
	String message() default "Fields value do not match!";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
	
	String field();
	String fieldMatch();
	@Target({ElementType.TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@interface List {
		FieldsValueMatch[] value();
	}
	
	
}
