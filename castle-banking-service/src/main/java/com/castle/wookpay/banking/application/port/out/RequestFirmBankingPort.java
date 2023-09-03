package com.castle.wookpay.banking.application.port.out;

import com.castle.wookpay.banking.domain.entity.FirmBankingRequestJpaEntity;
import com.castle.wookpay.banking.domain.enums.FirmBankingStatus;

public interface RequestFirmBankingPort {

	FirmBankingRequestJpaEntity createFirmBankingRequest(
			String fromBankName,
			String fromBankAccountNumber,
			String toBankName,
			String toBankAccountNumber,
			Long moneyAmount,
			FirmBankingStatus firmBankingStatus
	);
}
