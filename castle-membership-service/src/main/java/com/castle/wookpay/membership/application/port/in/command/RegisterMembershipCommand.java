package com.castle.wookpay.membership.application.port.in.command;

public record RegisterMembershipCommand(String name, String password, String email, String address) {
}
