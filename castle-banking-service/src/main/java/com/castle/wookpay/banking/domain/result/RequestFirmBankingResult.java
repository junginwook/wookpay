package com.castle.wookpay.banking.domain.result;

import lombok.Getter;

public record RequestFirmBankingResult(
		String firmBankingRequestId,
		String fromBankName,
		String fromBankAccountNumber,
		String toBankName,
		String toBankAccountNumber,
		int moneyAmount
) {

}
