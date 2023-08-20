package com.castle.wookpay.money.domain.feign.config;

import com.castle.wookpay.money.domain.feign.decoder.DemoFeignErrorDecoder;
import com.castle.wookpay.money.domain.feign.interceptor.DemoFeignInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoFeignConfig {

	@Bean
	public DemoFeignInterceptor feignInterceptor() {
		return DemoFeignInterceptor.of();
	}

	@Bean
	public DemoFeignErrorDecoder demoFeignErrorDecoder() {
		return new DemoFeignErrorDecoder();
	}
}
