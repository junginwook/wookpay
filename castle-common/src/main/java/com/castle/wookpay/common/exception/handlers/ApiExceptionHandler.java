package com.castle.wookpay.common.exception.handlers;

import com.castle.wookpay.common.exception.CustomException;
import com.castle.wookpay.common.exception.ErrorCode;
import com.castle.wookpay.common.http.ErrorResponse;
import com.castle.wookpay.common.http.ErrorResponse.ValidationError;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
				.body(makeResponse(errorCode));
	}

	private ErrorResponse makeResponse(ErrorCode errorCode) {
		return ErrorResponse.builder()
				.status(errorCode.getStatus())
				.message(errorCode.getMessage())
				.build();
	}

	@ExceptionHandler(
			MethodArgumentNotValidException.class
	)
	public ResponseEntity<ErrorResponse> requestValidationException(MethodArgumentNotValidException e) {

		ErrorCode errorCode = ErrorCode.INVALID_PARAMETER;

		return handleExceptionInternal(e, errorCode);
	}

	private ResponseEntity<ErrorResponse> handleExceptionInternal(BindException e, ErrorCode errorCode) {
		return ResponseEntity.status(errorCode.getHttpStatus())
				.body(makeErrorResponse(e, errorCode));
	}

	private ErrorResponse makeErrorResponse(BindException e, ErrorCode errorCode) {
		List<ValidationError> validationErrorList = e.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(ErrorResponse.ValidationError::of)
				.collect(Collectors.toList());

		return ErrorResponse.builder()
				.status(errorCode.getStatus())
				.message(errorCode.getMessage())
				.errors(validationErrorList)
				.build();
	}
}
