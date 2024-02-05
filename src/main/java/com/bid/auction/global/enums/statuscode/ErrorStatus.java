package com.bid.auction.global.enums.statuscode;

import org.springframework.http.HttpStatus;

public enum ErrorStatus implements BaseCode {
	// common
	_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
	_BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
	_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
	_FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

	// user
	_USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4001", "해당 사용자가 존재하지 않습니다."),

	// payment
	_UNEQUAL_ACTUAL_PAYMENT_AMOUNT(HttpStatus.BAD_REQUEST, "PAYMENT4001", "실 결제 금액과 결제 주문 금액이 다릅니다. 위변조 되었을 수 있습니다."),
	_PAYMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "PAYMENT4002", "해당 결제 내역이 존재하지 않습니다."),

	// payment status
	_PAYMENT_METHOD_NOT_FOUND(HttpStatus.BAD_REQUEST, "PAYMENT_METHOD4001", "유효하지 않은 결제 수단입니다."),

	// payment order
	_PAYMENT_ORDER_NOT_FOUND(HttpStatus.BAD_REQUEST, "PAYMENT_ORDER4001", "해당 결제 주문이 존재하지 않습니다."),

	// payment order status
	_PAYMENT_ORDER_STATUS_NOT_FOUND(HttpStatus.BAD_REQUEST, "PAYMENT_ORDER_STATUS4001", "유효하지 않은 결제 주문 상태입니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

	ErrorStatus(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public HttpStatus getStatus() {
		return httpStatus;
	}

	@Override
	public Integer getStatusValue() {
		return httpStatus.value();
	}
}
