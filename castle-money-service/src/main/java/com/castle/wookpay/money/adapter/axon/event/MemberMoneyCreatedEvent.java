package com.castle.wookpay.money.adapter.axon.event;

import jakarta.validation.constraints.NotNull;

public record MemberMoneyCreatedEvent(@NotNull String membershipId) {

}
