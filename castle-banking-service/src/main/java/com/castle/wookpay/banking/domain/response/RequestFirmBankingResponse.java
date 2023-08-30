package com.castle.wookpay.banking.domain.response;

public record RequestFirmBankingResponse(
		String firmBankingRequestId,
		String fromBankName,
		String fromBankAccountNumber,
		String toBankName,
		String toBankAccountNumber,
		Long moneyAmount
) {

}
