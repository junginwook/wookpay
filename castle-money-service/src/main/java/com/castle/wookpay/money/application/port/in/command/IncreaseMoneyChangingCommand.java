package com.castle.wookpay.money.application.port.in.command;

public record IncreaseMoneyChangingCommand(String targetMembershipId, Long amount) {

}
