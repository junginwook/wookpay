package com.castle.wookpay.banking.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterBankAccountRequest(
		@NotNull
		String membershipId,

		@NotNull
		String bankName,

		@NotNull
		@NotBlank
		String bankAccountNumber
) {

}
