package com.castle.wookpay.money.application.port.out.service.membership;

import com.castle.wookpay.money.adapter.out.microservice.membership.response.ValidateMembershipResponse;

public interface ValidateMembershipPort {
	ValidateMembershipResponse validateMembership(String membershipId);
}
