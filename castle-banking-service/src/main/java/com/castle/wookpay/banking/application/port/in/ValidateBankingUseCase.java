package com.castle.wookpay.banking.application.port.in;

import com.castle.wookpay.banking.domain.command.ValidateBankAccountCommand;
import com.castle.wookpay.banking.domain.result.ValidateBankingResult;

public interface ValidateBankingUseCase {
	ValidateBankingResult validateBankingAccount(ValidateBankAccountCommand command);
}
