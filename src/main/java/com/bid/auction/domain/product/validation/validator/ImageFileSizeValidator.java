package com.bid.auction.domain.product.validation.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bid.auction.domain.product.validation.annotation.ImageFileSize;
import com.bid.auction.global.enums.statuscode.ErrorStatus;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImageFileSizeValidator implements ConstraintValidator<ImageFileSize, List<MultipartFile>> {

	@Value("${spring.servlet.multipart.max-file-size}")
	private String maxSize;

	@Override
	public boolean isValid(List<MultipartFile> value, ConstraintValidatorContext context) {
		if(value == null) return true;
		boolean isValid = true;
		if (value.stream()
			.anyMatch(image -> image.getSize() >= convertToBytes(maxSize))) {
			isValid = false;
		}

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ErrorStatus._IMAGE_FILE_SIZE_EXCEEDED.toString())
				.addConstraintViolation();
		}

		return isValid;
	}

	@Override
	public void initialize(ImageFileSize constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	public static long convertToBytes(String sizeStr) {
		if (sizeStr.toUpperCase().endsWith("MB")) {
			int value = Integer.parseInt(sizeStr.substring(0, sizeStr.length() - 2).trim());
			return value * 1024L * 1024L; // MB to Bytes
		} else if (sizeStr.toUpperCase().endsWith("KB")) {
			int value = Integer.parseInt(sizeStr.substring(0, sizeStr.length() - 2).trim());
			return value * 1024L;
		} else if (sizeStr.toUpperCase().endsWith("B")) {
			return Integer.parseInt(sizeStr.substring(0, sizeStr.length() - 1).trim());
		}
		// 프로퍼티 변수가 잘못된 경우 (MB 단위 이상)
		log.error("지원하지 않는 프로퍼티 단위입니다. input = {}", sizeStr);
		throw new IllegalArgumentException("지원하지 않는 프로퍼티 단위입니다.");
	}
}
