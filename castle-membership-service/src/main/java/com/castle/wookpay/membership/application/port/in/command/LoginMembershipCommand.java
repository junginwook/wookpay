package com.castle.wookpay.membership.application.port.in.command;

import lombok.Builder;

@Builder
public record LoginMembershipCommand(String email, String password) {

}
