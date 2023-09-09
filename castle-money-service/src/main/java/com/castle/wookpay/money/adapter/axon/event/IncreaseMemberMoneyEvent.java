package com.castle.wookpay.money.adapter.axon.event;

public record IncreaseMemberMoneyEvent(String membershipId, int amount, String aggregateIdentifier) {

}
