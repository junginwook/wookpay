package com.castle.wookpay.money.domain.feign.client;

import com.castle.wookpay.money.domain.feign.common.dto.BaseResponseInfo;
import com.castle.wookpay.money.domain.feign.config.DemoFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
		name = "demo-client",
		url = "${feign.url.prefix}",
		configuration = DemoFeignConfig.class
)
public interface DemoFeignClient {

	@GetMapping("/member")
	ResponseEntity<BaseResponseInfo> callGet(@RequestHeader("CustomHeaderName") String customHeader,
						@RequestParam("name") String name,
						@RequestParam("age") Long age);
}
