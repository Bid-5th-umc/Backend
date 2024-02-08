package com.bid.auction.global.exception;

import org.springframework.http.HttpStatus;

import com.bid.auction.global.enums.statuscode.BaseCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeneralException extends RuntimeException {
	private final BaseCode errorStatus;

	public String getErrorCode() {
		return errorStatus.getCode();
	}

	public String getErrorReason() {
		return errorStatus.getMessage();
	}

	public HttpStatus getHttpStatus() {
		return errorStatus.getStatus();
	}
}
