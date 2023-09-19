package com.castle.wookpay.banking.application.port.in;

import com.castle.wookpay.banking.domain.command.RequestFirmBankingCommand;
import com.castle.wookpay.banking.domain.command.UpdateFirmBankingCommand;

public interface UpdateFirmBankingUseCase {
	void updateFirmBankingByEvent(UpdateFirmBankingCommand command);
}
