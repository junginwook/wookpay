package com.castle.wookpay.money.adapter.out.microservice.banking.response;

public record RequestFirmBankingResponse(
		String membershipId,
		String fromBankName,
		String fromBankAccountNumber,
		String toBankName,
		String toBankAccountNumber,
		Long moneyAmount
) {

}
