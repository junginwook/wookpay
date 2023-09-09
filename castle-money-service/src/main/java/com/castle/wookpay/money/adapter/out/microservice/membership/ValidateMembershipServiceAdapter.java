package com.castle.wookpay.money.adapter.out.microservice.membership;

import com.castle.wookpay.common.annotation.ExternalSystemAdapter;
import com.castle.wookpay.money.adapter.out.microservice.membership.response.ValidateMembershipResponse;
import com.castle.wookpay.money.application.port.out.service.membership.ValidateMembershipPort;
import com.castle.wookpay.money.domain.feign.membership.MembershipFeignClient;
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
