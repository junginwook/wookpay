package com.castle.wookpay.banking;

import com.castle.wookpay.banking.domain.feign.FeignCustomLogger;
import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.castle.wookpay.banking.domain.feign")
public class OpenFeignConfig {

	@Bean
	public Logger feignCustomLogger() {
		return new FeignCustomLogger();
	}
}
