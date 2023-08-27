package com.castle.wookpay.membership.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterMembershipRequest(@NotBlank @NotNull String name, @NotBlank @NotNull String password, @NotBlank @NotNull String email, @NotBlank @NotNull String address) {

}
