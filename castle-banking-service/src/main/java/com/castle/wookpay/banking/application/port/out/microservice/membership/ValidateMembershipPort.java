package com.castle.wookpay.banking.application.port.out.microservice.membership;

import com.castle.wookpay.banking.application.port.out.microservice.membership.response.ValidateMembershipResponse;

public interface ValidateMembershipPort {
	ValidateMembershipResponse validateMembership(String membershipId);
}
