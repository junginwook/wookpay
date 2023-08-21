package com.castle.wookpay.money.application.port.out.service.membership;

import com.castle.wookpay.money.adapter.out.service.membership.response.FindMembershipResponse;

public interface FindMembershipPort {
	FindMembershipResponse findMembership(String membershipId);
}
