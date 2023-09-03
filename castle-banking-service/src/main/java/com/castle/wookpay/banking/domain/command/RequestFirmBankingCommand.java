package com.castle.wookpay.banking.domain.command;

import lombok.Builder;

@Builder
public record RequestFirmBankingCommand(
		String membershipId,
		String fromBankName,
		String fromBankAccountNumber,
		String toBankName,
		String toBankAccountNumber,
		Long moneyAmount
) {

}
