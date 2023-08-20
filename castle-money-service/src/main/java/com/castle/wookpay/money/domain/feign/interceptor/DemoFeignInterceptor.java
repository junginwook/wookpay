package com.castle.wookpay.money.domain.feign.interceptor;

import feign.Request.HttpMethod;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor(staticName = "of")
public class DemoFeignInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {

		if (template.method() == HttpMethod.GET.name()) {
			System.out.println("[GET] [DemoFeignInterceptor] queries : " + template.queries());
			return;
		}

		String encodedString = StringUtils.toEncodedString(template.body(), StandardCharsets.UTF_8);
		System.out.println("[POST] [DemoFeignInterceptor] requestBody : " + encodedString);

		//추가적으로 필요한 로직 추가

		template.body(encodedString);

	}
}
