package com.castle.wookpay.banking.domain.response;

public record RegisterBankAccountResponse(
		String registeredBankAccountId,
		String membershipId,
		String bankName,
		String bankAccountNumber
) {

}
