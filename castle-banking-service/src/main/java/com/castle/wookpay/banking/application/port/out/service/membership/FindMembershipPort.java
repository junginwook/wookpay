package com.castle.wookpay.banking.application.port.out.service.membership;

import com.castle.wookpay.banking.application.port.out.service.membership.response.FindMembershipResponse;

public interface FindMembershipPort {
	FindMembershipResponse findMembership(String membershipId);
}
