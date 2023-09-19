package com.castle.wookpay.banking.application.port.in;

import com.castle.wookpay.banking.domain.command.RequestFirmBankingCommand;
import com.castle.wookpay.banking.domain.result.RequestFirmBankingResult;

public interface RequestFirmBankingUseCase {
	RequestFirmBankingResult requestFirmBanking(RequestFirmBankingCommand command);

	void requestFirmBankingByEvent(RequestFirmBankingCommand command);
}
