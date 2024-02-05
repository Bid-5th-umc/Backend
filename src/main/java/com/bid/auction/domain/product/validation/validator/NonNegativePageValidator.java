package com.bid.auction.domain.product.validation.validator;

import org.springframework.stereotype.Component;

import com.bid.auction.domain.product.validation.annotation.NonNegative;
import com.bid.auction.global.enums.statuscode.ErrorStatus;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NonNegativePageValidator implements ConstraintValidator<NonNegative, Integer> {
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		boolean isValid = value >= 0;
		log.info("isValid = {}", isValid);
		if(!isValid){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorStatus._NOT_POSITIVE_NUMBER.getMessage().toString()).addConstraintViolation();
		}
		return isValid;
	}

	@Override
	public void initialize(NonNegative constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
}
