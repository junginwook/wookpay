package com.castle.wookpay.money.domain.feign.banking;

import feign.Request.HttpMethod;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.nio.charset.StandardCharsets;
import org.apache.commons.lang3.StringUtils;

public class BankingFeignInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {

		if (template.method() == HttpMethod.GET.name()) {
			return;
		}

		String encodedString = StringUtils.toEncodedString(template.body(), StandardCharsets.UTF_8);

		//추가적으로 필요한 로직 추가
		template.body(encodedString);
	}
}
