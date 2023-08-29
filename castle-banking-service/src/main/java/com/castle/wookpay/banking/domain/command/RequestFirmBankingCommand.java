package com.castle.wookpay.banking.domain.command;

import lombok.Builder;

@Builder
public record RequestFirmBankingCommand(
		String fromBankName,
		String fromBankAccountNumber,
		String toBankName,
		String toBankAccountNumber,
		int moneyAmount
) {

}