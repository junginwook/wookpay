package com.castle.wookpay.common.http;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {
	private T result;
	private  int status;

	@Builder
	public ApiResponse(T result, HttpStatus status) {
		this.result = result;
		this.status = status.value();
	}

	public ApiResponse(HttpStatus status) {
		this.status = status.value();
		this.result = null;
	}
}
