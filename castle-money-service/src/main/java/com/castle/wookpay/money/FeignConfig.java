package com.castle.wookpay.money;

import com.castle.wookpay.money.domain.feign.logger.FeignCustomLogger;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

	@Bean
	public Logger feignCustomLogger() {
		return new FeignCustomLogger();
	}
}
