package com.castle.wookpay.banking.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestFirmBankingRequest(
		@NotNull
		@NotBlank
		String membershipId,

		@NotNull
		@NotBlank
		String fromBankName,

		@NotNull
		@NotBlank
		String fromBankAccountNumber,

		@NotNull
		@NotBlank
		String toBankName,

		@NotNull
		@NotBlank
		String toBankAccountNumber,

		@NotNull
		Long moneyAmount
) {

}
