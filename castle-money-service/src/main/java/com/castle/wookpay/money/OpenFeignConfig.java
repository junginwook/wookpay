package com.castle.wookpay.money;

import com.castle.wookpay.money.domain.feign.FeignCustomLogger;
import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.castle.wookpay.money.domain.feign")
public class OpenFeignConfig {

	@Bean
	public Logger feignCustomLogger() {
		return new FeignCustomLogger();
	}
}
