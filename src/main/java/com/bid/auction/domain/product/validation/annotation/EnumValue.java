package com.bid.auction.domain.product.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bid.auction.domain.product.validation.validator.EnumValueValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValueValidator.class)
public @interface EnumValue {
	String message() default "Invalid enum value";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	Class<? extends Enum<?>> enumClass();
}
