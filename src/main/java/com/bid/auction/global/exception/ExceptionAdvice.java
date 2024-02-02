package com.bid.auction.global.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bid.auction.global.enums.statuscode.ErrorStatus;
import com.bid.auction.global.response.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = GeneralException.class)
	public ResponseEntity<Object> handleGeneralException(GeneralException exception, HttpServletRequest request) {
		return handleExceptionInternal(exception, HttpHeaders.EMPTY, request);
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
}
