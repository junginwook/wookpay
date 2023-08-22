package com.castle.wookpay.money.domain.feign;

import static feign.Util.decodeOrDefault;
import static feign.Util.valuesOrEmpty;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import java.io.IOException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeignCustomLogger extends Logger {

	@Override
	protected void log(String configKey, String format, Object... args) {
		// log를 어떤 형식으로 남길지 정해준다
		System.out.println(String.format(methodTag(configKey) + format, args));
	}

	//request
	@Override
	protected void logRequest(String configKey, Level logLevel, Request request) {
		System.out.println(request);
	}

	//response
	@Override
	protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
		String protocolVersion = resolveProtocolVersion(response.protocolVersion());
		String reason =
				response.reason() != null && logLevel.compareTo(Level.NONE) > 0 ? " " + response.reason()
						: "";
		int status = response.status();
		log(configKey, "<--- %s %s%s (%sms)", protocolVersion, status, reason, elapsedTime);
		if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {

			for (String field : response.headers().keySet()) {
				if (shouldLogResponseHeader(field)) {
					for (String value : valuesOrEmpty(response.headers(), field)) {
						log(configKey, "%s: %s", field, value);
					}
				}
			}

			int bodyLength = 0;
			if (response.body() != null && !(status == 204 || status == 205)) {
				// HTTP 204 No Content "...response MUST NOT include a message-body"
				// HTTP 205 Reset Content "...response MUST NOT include an entity"
				if (logLevel.ordinal() >= Level.FULL.ordinal()) {
					log(configKey, ""); // CRLF
				}
				byte[] bodyData = Util.toByteArray(response.body().asInputStream());
				bodyLength = bodyData.length;
				if (logLevel.ordinal() >= Level.FULL.ordinal() && bodyLength > 0) {
					log(configKey, "%s", decodeOrDefault(bodyData, Util.UTF_8, "Binary data"));
				}
				log(configKey, "<--- END HTTP (%s-byte body)", bodyLength);
				return response.toBuilder().body(bodyData).build();
			} else {
				log(configKey, "<--- END HTTP (%s-byte body)", bodyLength);
			}
		}
		return response;
	}
}
