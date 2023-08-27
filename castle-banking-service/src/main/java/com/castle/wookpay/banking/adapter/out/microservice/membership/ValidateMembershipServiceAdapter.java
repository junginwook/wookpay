package com.castle.wookpay.banking.adapter.out.microservice.membership;

import com.castle.wookpay.banking.application.port.out.microservice.membership.ValidateMembershipPort;
import com.castle.wookpay.banking.application.port.out.microservice.membership.response.ValidateMembershipResponse;
import com.castle.wookpay.banking.domain.feign.membership.MembershipFeignClient;
import com.castle.wookpay.common.annotation.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class ValidateMembershipServiceAdapter implements ValidateMembershipPort {
	private final MembershipFeignClient membershipFeignClient;

	@Override
	public ValidateMembershipResponse validateMembership(String membershipId) {
		return membershipFeignClient.findValidMember(membershipId).getResult();
	}
}
