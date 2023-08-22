package com.castle.wookpay.money.adapter.out.service.membership;

import com.castle.wookpay.common.annotation.ExternalSystemAdapter;
import com.castle.wookpay.money.adapter.out.service.membership.response.FindMembershipResponse;
import com.castle.wookpay.money.application.port.out.service.membership.FindMembershipPort;
import com.castle.wookpay.money.domain.feign.membership.MembershipFeignClient;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MembershipServiceAdapter implements FindMembershipPort {

	private final MembershipFeignClient membershipFeignClient;

	@Override
	public FindMembershipResponse findMembership(String membershipId) {
		return membershipFeignClient.findValidMember(membershipId).getResult();
	}
}
