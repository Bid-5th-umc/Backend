package com.bid.auction.domain.product.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bid.auction.domain.product.validation.validator.ImageFileSizeValidator;
import com.bid.auction.domain.product.validation.validator.ValidBuyoutPriceValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ValidBuyoutPriceValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBuyoutPrice {

	String message() default ("즉시 구매 가격은 최소 입찰가보다 높아야 합니다.");

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
