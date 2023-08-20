package com.castle.wookpay.money.domain.feign.client;

import com.castle.wookpay.common.http.ApiResponse;
import com.castle.wookpay.money.adapter.out.service.membership.response.FindMembershipResponse;
import com.castle.wookpay.money.domain.feign.common.dto.BaseResponseInfo;
import com.castle.wookpay.money.domain.feign.config.DemoFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
		name = "membership-client",
		url = "${feign.url.prefix}",
		configuration = DemoFeignConfig.class
)
public interface MembershipFeignClient {

	@GetMapping("/member/{memberId}")
	ApiResponse<FindMembershipResponse> findValidMember(
			@PathVariable("memberId") String memberId
	);
}
