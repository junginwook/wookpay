package com.castle.wookpay.common.exception.handlers;

import com.castle.wookpay.common.exception.CustomException;
import com.castle.wookpay.common.exception.ErrorCode;
import com.castle.wookpay.common.http.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(
			CustomException.class
	)
	public ResponseEntity<ErrorResponse> customException(CustomException customException) {
		return handlerExceptionInternal(customException.getErrorCode());
	}

	private ResponseEntity<ErrorResponse> handlerExceptionInternal(ErrorCode errorCode) {
		return ResponseEntity.status(errorCode.getHttpStatus())
				.body(makeErrorResponse(errorCode));
	}

	private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
		return ErrorResponse.builder()
				.status(errorCode.getStatus())
				.message(errorCode.getMessage())
				.build();
	}
}
