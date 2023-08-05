package com.castle.wookpay.common.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {

	private final boolean success = false;

	private final int status;

	private final String message;

	@JsonInclude(JsonInclude.Include.NON_EMPTY) //errors 가 없다면 응답으로 내려가지 않도록
	private final List<ValidationError> errors;

	@Builder
	public record ValidationError(String filed, String message) {

		public static ValidationError of(final FieldError fieldError) {
			return ValidationError.builder()
					.filed(fieldError.getField())
					.message(fieldError.getDefaultMessage())
					.build();
		}
	}
}
