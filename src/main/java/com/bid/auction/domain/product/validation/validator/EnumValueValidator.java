package com.bid.auction.domain.product.validation.validator;

import org.springframework.stereotype.Component;

import com.bid.auction.domain.product.validation.annotation.EnumValue;
import com.bid.auction.global.enums.statuscode.ErrorStatus;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {
	private Class<? extends Enum<?>> enumClass;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		boolean isValid = false;

		log.info("Enum Validation , value = {}", value);

		try {
			Enum<?>[] enumConstants = enumClass.getEnumConstants();
			for (Enum<?> enumConstant : enumConstants) {
				if (enumConstant.name().equals(value)) {
					isValid = true; // Enum에 해당 값이 존재하면 유효
					break;
				}
			}
		} catch (IllegalArgumentException ex) {
			log.error("Enum 검증 도중 오류 발생", ex);
		}

		if(!isValid){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorStatus._INVALID_ENUM_VALUE.toString()).addConstraintViolation();
		}

		return isValid;
	}

	@Override
	public void initialize(EnumValue constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
}
