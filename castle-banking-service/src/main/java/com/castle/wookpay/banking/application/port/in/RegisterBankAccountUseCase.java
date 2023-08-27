package com.castle.wookpay.banking.application.port.in;

import com.castle.wookpay.banking.domain.command.RegisterBankAccountCommand;
import com.castle.wookpay.banking.domain.result.RegisterBankAccountResult;

public interface RegisterBankAccountUseCase {
	RegisterBankAccountResult registerBankAccount(RegisterBankAccountCommand command);
}
