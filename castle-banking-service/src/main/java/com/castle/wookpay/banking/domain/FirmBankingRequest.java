package com.castle.wookpay.banking.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FirmBankingRequest {

	private final String firmBankingRequestId;
	private final String fromBankName;
	private final String fromBankAccountNumber;
	private final String toBankName;
	private final String toBankAccountNumber;
	private final int moneyAmount;
	private final int firmBankingStatus;

	public static FirmBankingRequest generateFirmBankingRequest(
			String firmBankingRequestId,
			String fromBankName,
			String fromBankAccountNumber,
			String toBankName,
			String toBankAccountNumber,
			int moneyAmount,
			int firmBankingStatus
	) {
		return new FirmBankingRequest(
			firmBankingRequestId,
			fromBankName,
			fromBankAccountNumber,
			toBankName,
			toBankAccountNumber,
			moneyAmount,
			firmBankingStatus
		);
	}
}
