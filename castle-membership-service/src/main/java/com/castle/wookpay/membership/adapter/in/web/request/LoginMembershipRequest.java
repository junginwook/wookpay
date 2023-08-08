package com.castle.wookpay.membership.adapter.in.web.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginMembershipRequest(@Email @NotNull @NotEmpty String email, @NotNull @NotEmpty String password) {
}
