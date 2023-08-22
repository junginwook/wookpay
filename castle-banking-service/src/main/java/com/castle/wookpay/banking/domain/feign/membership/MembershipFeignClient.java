package com.castle.wookpay.banking.domain.feign.membership;

import com.castle.wookpay.banking.application.port.out.service.membership.response.FindMembershipResponse;
import com.castle.wookpay.common.http.ApiResponse;
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
	ApiResponse<FindMembershipResponse> findValidMember(
			@PathVariable("memberId") String memberId
	);
}
