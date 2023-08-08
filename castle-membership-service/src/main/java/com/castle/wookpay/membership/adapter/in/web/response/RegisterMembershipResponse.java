package com.castle.wookpay.membership.adapter.in.web.response;

import lombok.Builder;

@Builder
public record RegisterMembershipResponse(String membershipId, String name, String email, String address) {

}
