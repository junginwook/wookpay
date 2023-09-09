package com.castle.wookpay.banking.application.service;

import com.castle.wookpay.banking.application.port.in.ValidateBankingUseCase;
import com.castle.wookpay.banking.application.port.out.FindBankingPort;
import com.castle.wookpay.banking.domain.command.ValidateBankAccountCommand;
import com.castle.wookpay.banking.domain.entity.BankAccountJpaEntity;
import com.castle.wookpay.banking.domain.result.ValidateBankingResult;
import com.castle.wookpay.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ValidateBankingService implements ValidateBankingUseCase {
	private final FindBankingPort findBankingPort;

	@Override
	public ValidateBankingResult validateBankingAccount(ValidateBankAccountCommand command) {
		BankAccountJpaEntity bankingAccount = findBankingPort.findBankingAccount(command.membershipId());

		return new ValidateBankingResult(bankingAccount.getBankName(), bankingAccount.getBankAccountNumber());
	}
}
