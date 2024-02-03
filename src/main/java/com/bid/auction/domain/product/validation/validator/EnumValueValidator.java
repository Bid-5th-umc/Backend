package com.bid.auction.domain.product.validation.validator;

import com.bid.auction.domain.product.validation.annotation.EnumValue;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {
	private Class<? extends Enum<?>> enumClass;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		try {
			Enum<?>[] enumConstants = enumClass.getEnumConstants();
			for (Enum<?> enumConstant : enumConstants) {
				if (enumConstant.name().equals(value)) {
					return true; // Enum에 해당 값이 존재하면 유효
				}
			}
			return false; // 존재하지 않으면 유효하지 않음
		} catch (IllegalArgumentException ex) {
			return false; // 예외 발생 시 유효하지 않음
		}
	}

	@Override
	public void initialize(EnumValue constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
}
