package com.castle.wookpay.common.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

@Builder
public record ErrorResponse(int status, String message, @JsonInclude(JsonInclude.Include.NON_EMPTY) List<ValidationError> errors) {

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
