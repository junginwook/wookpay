package com.castle.wookpay.banking.application.service;

import com.castle.wookpay.banking.adapter.out.external.bank.request.ExGetBankAccountRequest;
import com.castle.wookpay.banking.adapter.out.external.bank.response.ExGetBankAccountResponse;
import com.castle.wookpay.banking.application.port.in.RegisterBankAccountUseCase;
import com.castle.wookpay.banking.application.port.out.external.bank.RequestBankAccountInfoPort;
import com.castle.wookpay.banking.application.port.out.persistence.CheckDuplicateBankAccountPort;
import com.castle.wookpay.banking.application.port.out.persistence.RegisterBankAccountPort;
import com.castle.wookpay.banking.application.port.out.microservice.membership.ValidateMembershipPort;
import com.castle.wookpay.banking.domain.command.RegisterBankAccountCommand;
import com.castle.wookpay.banking.domain.entity.BankAccountJpaEntity;
import com.castle.wookpay.banking.domain.result.RegisterBankAccountResult;
import com.castle.wookpay.common.annotation.UseCase;
import com.castle.wookpay.common.exception.CustomException;
import com.castle.wookpay.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

	private final ValidateMembershipPort findMembershipPort;
	private final RequestBankAccountInfoPort requestBankAccountInfoPort;
	private final RegisterBankAccountPort registerBankAccountPort;
	private final CheckDuplicateBankAccountPort checkDuplicateBankAccountPort;
	@Override
	public RegisterBankAccountResult registerBankAccount(RegisterBankAccountCommand command) {
		// 유효한 유저인지 체크
		findMembershipPort.validateMembership(command.membershipId());

		// 중복된 계좌인지 체크
		checkDuplicateBankAccountPort.existBankAccount(command.bankName(), command.bankAccountNumber());


		// 유효한 계좌인지 체크
		final ExGetBankAccountResponse getBankAccountResponse = requestBankAccountInfoPort.getBankAccountInfo(
				new ExGetBankAccountRequest(command.bankName(), command.bankAccountNumber())
		);

		BankAccountJpaEntity registeredBankAccountJpaEntity;
		if (getBankAccountResponse.isValid()) {
			registeredBankAccountJpaEntity = registerBankAccountPort.registerBankAccount(
					command.membershipId(),
					command.bankName(),
					command.bankAccountNumber()
			);
		} else {
			throw new CustomException(ErrorCode.RESOURCE_NOT_VALID);
		}

		return new RegisterBankAccountResult(
				registeredBankAccountJpaEntity.getRegisteredBankAccountId().toString(),
				command.membershipId(),
				command.bankName(),
				command.bankAccountNumber()
		);
	}
}
