package com.castle.wookpay.banking.domain.command;

public record RegisterBankAccountCommand(
		String membershipId,
		String bankName,
		String bankAccountNumber
) {

}
