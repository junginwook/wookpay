package com.castle.wookpay.membership.domain.response;

import lombok.Builder;

@Builder
public record RegisterMembershipResponse(String membershipId, String name, String email, String address) {

}
