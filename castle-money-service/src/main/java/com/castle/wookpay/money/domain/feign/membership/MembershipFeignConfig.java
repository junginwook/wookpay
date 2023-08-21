package com.castle.wookpay.money.domain.feign.membership;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MembershipFeignConfig {

	@Bean
	public MembershipFeignInterceptor feignInterceptor() {
		return new MembershipFeignInterceptor();
	}

	@Bean
	public MembershipFeignErrorDecoder demoFeignErrorDecoder() {
		return new MembershipFeignErrorDecoder();
	}
}
