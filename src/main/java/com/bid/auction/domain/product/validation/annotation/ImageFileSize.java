package com.bid.auction.domain.product.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bid.auction.domain.product.validation.validator.ImageFileSizeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ImageFileSizeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageFileSize {
	String message() default ("사진 파일의 크기가 너무 큽니다.");

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
