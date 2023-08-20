package com.castle.wookpay.money.application.service;

import com.castle.wookpay.money.domain.feign.client.MembershipFeignClient;
import com.castle.wookpay.money.domain.feign.common.dto.BaseResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoService {

	private final MembershipFeignClient demoFeignClient;

	public String get() {
		ResponseEntity<BaseResponseInfo> response = demoFeignClient.callGet("CustomHeader", "CustomName", 10L);
		System.out.println("Name: " + response.getBody().getName());
		System.out.println("Age: " + response.getBody().getAge());
		System.out.println("Header: " + response.getBody().getHeader());
		return "get";
	}
}
