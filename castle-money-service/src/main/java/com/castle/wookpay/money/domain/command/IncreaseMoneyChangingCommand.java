package com.castle.wookpay.money.domain.command;

public record IncreaseMoneyChangingCommand(String targetMembershipId, int amount, String aggregateIdentifier) {

}
