package com.bid.auction.global.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bid.auction.global.enums.statuscode.ErrorStatus;
import com.bid.auction.global.response.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = GeneralException.class)
	public ResponseEntity<Object> handleGeneralException(GeneralException exception, HttpServletRequest request) {
		return handleExceptionInternal(exception, HttpHeaders.EMPTY, request);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		log.info("handleGeneralException 발생 ={}", exception);
		Map<String, String> errors = new LinkedHashMap<>();
		exception.getBindingResult().getFieldErrors().stream()
			.forEach(fieldError -> {

				String fieldName = fieldError.getField();
				String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
				errors.merge(fieldName, errorMessage,
					(existingErrorMessage, newErrorMessage) -> existingErrorMessage + ", " + newErrorMessage);
			});

		exception.getBindingResult().getGlobalErrors().stream()
			.forEach(globalError -> {
				log.info("globalError = {}", globalError);
				String objectName = globalError.getObjectName();
				String errorMessage = Optional.ofNullable(globalError.getDefaultMessage()).orElse("");
				errors.merge(objectName, errorMessage,
					(existingErrorMessage, newErrorMessage) -> existingErrorMessage + ", " + newErrorMessage);
			});

		return handleExceptionInternalArgs(exception, HttpHeaders.EMPTY, ErrorStatus.valueOf("_BAD_REQUEST"), request,
			errors);
	}

	@ExceptionHandler
	public ResponseEntity<Object> handlingException(Exception exception, WebRequest request) {
		return handleExceptionInternal(exception, ErrorStatus._INTERNAL_SERVER_ERROR, HttpHeaders.EMPTY,
			ErrorStatus._INTERNAL_SERVER_ERROR.getHttpStatus(), request, exception.getMessage());
	}

	private ResponseEntity<Object> handleExceptionInternal(GeneralException exception, HttpHeaders headers,
		HttpServletRequest request) {
		ApiResponse<Object> body = ApiResponse.onFailure(exception.getErrorCode(), exception.getErrorReason(), null);
		WebRequest webRequest = new ServletWebRequest(request);
		return super.handleExceptionInternal(exception, body, headers, exception.getHttpStatus(), webRequest);
	}

	private ResponseEntity<Object> handleExceptionInternal(Exception exception, ErrorStatus errorStatus,
		HttpHeaders headers, HttpStatus status, WebRequest request, String errorPoint) {
		ApiResponse<String> body = ApiResponse.onFailure(errorStatus.getCode(), errorStatus.getMessage(), errorPoint);
		return super.handleExceptionInternal(exception, body, headers, status, request);
	}

	private ResponseEntity<Object> handleExceptionInternalArgs(Exception e, HttpHeaders headers,
		ErrorStatus errorCommonStatus, WebRequest request, Map<String, String> errorArgs) {
		ApiResponse<Object> body = ApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(),
			errorArgs);
		return super.handleExceptionInternal(e, body, headers, errorCommonStatus.getHttpStatus(), request);
	}
}
