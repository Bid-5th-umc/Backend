package com.bid.auction.domain.product.validation.validator;

import org.springframework.stereotype.Component;

import com.bid.auction.domain.product.validation.annotation.ValidBuyoutPrice;
import com.bid.auction.domain.product.web.dto.AuctionPostRequestDTO;
import com.bid.auction.global.enums.statuscode.ErrorStatus;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidBuyoutPriceValidator implements ConstraintValidator<ValidBuyoutPrice, AuctionPostRequestDTO.CreateAuctionPostDTO> {
	@Override
	public boolean isValid(AuctionPostRequestDTO.CreateAuctionPostDTO value, ConstraintValidatorContext context) {
		if(value == null) return true;
		boolean isValid = value.getBuyoutPrice() > value.getInitialBid();

		if(!isValid){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorStatus._BUYOUT_PRICE_NOT_VALID.toString())
				.addConstraintViolation();
		}
		return isValid;
	}

	@Override
	public void initialize(ValidBuyoutPrice constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
}
