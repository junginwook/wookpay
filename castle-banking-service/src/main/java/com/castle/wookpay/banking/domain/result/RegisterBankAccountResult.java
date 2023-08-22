package com.castle.wookpay.banking.domain.result;

public record RegisterBankAccountResult(
		String registeredBankAccountId,
		String membershipId,
		String bankName,
		String bankAccountNumber,
		Boolean linkedStatusIsValid
) {
}
