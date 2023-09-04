package com.castle.wookpay.money.adapter.out.microservice.banking.request;

import lombok.Builder;

@Builder
public record RequestFirmBankingRequest(
		String membershipId,
		String fromBankName,
		String fromBankAccountNumber,
		String toBankName,
		String toBankAccountNumber,
		Long moneyAmount
) {

}
