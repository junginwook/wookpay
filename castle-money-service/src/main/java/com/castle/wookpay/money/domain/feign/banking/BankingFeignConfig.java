package com.castle.wookpay.money.domain.feign.banking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankingFeignConfig {

	@Bean
	public BankingFeignInterceptor bankingFeignInterceptor() {
		return new BankingFeignInterceptor();
	}

	@Bean
	public BankingFeignErrorDecoder bankingDemoFeignErrorDecoder() {
		return new BankingFeignErrorDecoder();
	}
}
