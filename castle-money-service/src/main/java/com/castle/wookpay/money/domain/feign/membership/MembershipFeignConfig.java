package com.castle.wookpay.money.domain.feign.membership;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MembershipFeignConfig {

	@Bean
	public MembershipFeignInterceptor membershipfeignInterceptor() {
		return new MembershipFeignInterceptor();
	}

	@Bean
	public MembershipFeignErrorDecoder membershipDemoFeignErrorDecoder() {
		return new MembershipFeignErrorDecoder();
	}
}
