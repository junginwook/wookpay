package com.castle.wookpay.banking.domain.feign.membership;

import com.castle.wookpay.common.exception.CustomException;
import com.castle.wookpay.common.exception.ErrorCode;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class MembershipFeignErrorDecoder implements ErrorDecoder {

	private final ErrorDecoder errorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {
		HttpStatus httpStatus = HttpStatus.resolve(response.status());

		if (httpStatus == HttpStatus.NOT_FOUND) {
			throw new CustomException(ErrorCode.NOT_FOUND_FROM_REMOTE);
		}

		return errorDecoder.decode(methodKey, response);
	}
}
