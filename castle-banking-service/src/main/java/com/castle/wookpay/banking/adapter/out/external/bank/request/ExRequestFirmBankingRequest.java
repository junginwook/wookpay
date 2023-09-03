package com.castle.wookpay.banking.adapter.out.external.bank.request;

public record ExRequestFirmBankingRequest(
		String fromBankName,
		String fromBankAccountNumber,
		String toBankName,
		String toBankAccountNumber) {
}
