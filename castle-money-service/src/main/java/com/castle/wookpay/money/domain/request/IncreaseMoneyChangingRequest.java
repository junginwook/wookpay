package com.castle.wookpay.money.domain.request;

public record IncreaseMoneyChangingRequest(String targetMembershipId, int amount, String aggregateIdentifier){

}
