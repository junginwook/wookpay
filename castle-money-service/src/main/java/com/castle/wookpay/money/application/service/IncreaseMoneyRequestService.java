package com.castle.wookpay.money.application.service;

import com.castle.wookpay.common.annotation.UseCase;
import com.castle.wookpay.money.adapter.out.service.membership.response.ValidateMembershipResponse;
import com.castle.wookpay.money.application.port.in.IncreaseMoneyUseCase;
import com.castle.wookpay.money.application.port.out.service.banking.ValidateBankingPort;
import com.castle.wookpay.money.application.port.out.service.membership.ValidateMembershipPort;
import com.castle.wookpay.money.domain.command.IncreaseMoneyChangingCommand;
import com.castle.wookpay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class IncreaseMoneyRequestService implements IncreaseMoneyUseCase {
	private final ValidateMembershipPort validateMembershipPort;
	private final ValidateBankingPort validateBankingPort;

	@Override
	public MoneyChangingRequest increaseMoney(IncreaseMoneyChangingCommand command) {
		//유효한 유저인지 체크
		ValidateMembershipResponse membership = validateMembershipPort.validateMembership(command.targetMembershipId());

		//유효한 뱅킹 계정인지 확인


		return null;
	}
}
