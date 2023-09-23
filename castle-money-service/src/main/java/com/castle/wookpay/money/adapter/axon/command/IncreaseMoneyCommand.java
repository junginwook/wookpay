package com.castle.wookpay.money.adapter.axon.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record IncreaseMoneyCommand(String targetMembershipId, int balance, @TargetAggregateIdentifier String aggregateIdentifier) {

}
