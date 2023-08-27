package com.castle.wookpay.banking.application.port.out.microservice.membership.response;

public record ValidateMembershipResponse(String email, String name, String membershipId) {
}
