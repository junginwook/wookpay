package com.castle.wookpay.money.domain.feign.membership;

import com.castle.wookpay.common.http.ApiResponse;
import com.castle.wookpay.money.adapter.out.microservice.membership.response.ValidateMembershipResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
		name = "membership-client",
		url = "${feign.url.membership}",
		configuration = MembershipFeignConfig.class
)
public interface MembershipFeignClient {

	@GetMapping("/internal/v1/member/{memberId}")
	ApiResponse<ValidateMembershipResponse> findValidMember(
			@PathVariable("memberId") String memberId
	);
}
