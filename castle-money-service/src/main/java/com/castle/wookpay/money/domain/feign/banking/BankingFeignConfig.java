package com.castle.wookpay.money.domain.feign.banking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankingFeignConfig {

	@Bean
	public BankingFeignInterceptor feignInterceptor() {
		return new BankingFeignInterceptor();
	}

	@Bean
	public BankingFeignErrorDecoder demoFeignErrorDecoder() {
		return new BankingFeignErrorDecoder();
	}
}
