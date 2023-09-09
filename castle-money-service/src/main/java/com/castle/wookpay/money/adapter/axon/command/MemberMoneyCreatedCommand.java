package com.castle.wookpay.money.adapter.axon.command;

import jakarta.validation.constraints.NotNull;

public record MemberMoneyCreatedCommand(@NotNull String membershipId) {

}
