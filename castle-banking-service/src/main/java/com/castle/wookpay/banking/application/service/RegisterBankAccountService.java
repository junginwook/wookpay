package com.castle.wookpay.banking.application.service;

import com.castle.wookpay.banking.application.port.in.RegisterBankAccountUseCase;
import com.castle.wookpay.banking.application.port.out.service.membership.FindMembershipPort;
import com.castle.wookpay.banking.domain.command.RegisterBankAccountCommand;
import com.castle.wookpay.banking.application.port.out.service.membership.response.FindMembershipResponse;
import com.castle.wookpay.banking.domain.result.RegisterBankAccountResult;
import com.castle.wookpay.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

	private final FindMembershipPort findMembershipPort;

	@Override
	public RegisterBankAccountResult registerBankAccount(RegisterBankAccountCommand command) {

		FindMembershipResponse membership = findMembershipPort.findMembership(command.membershipId());

		return null;
	}
}
