package com.castle.wookpay.money.adapter.out.service.membership.response;

public record ValidateMembershipResponse(String email, String name, String membershipId, String bankName, String bankAccountNumber) {

}
