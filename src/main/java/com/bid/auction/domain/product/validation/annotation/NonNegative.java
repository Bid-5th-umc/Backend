package com.bid.auction.domain.product.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bid.auction.domain.product.validation.validator.NonNegativePageValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = NonNegativePageValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NonNegative {
	String message() default ("음수일 수 없습니다.");

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
