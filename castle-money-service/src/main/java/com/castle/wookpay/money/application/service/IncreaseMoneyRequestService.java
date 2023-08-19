package com.castle.wookpay.money.application.service;

import com.castle.wookpay.common.annotation.UseCase;
import com.castle.wookpay.money.adapter.out.service.membership.MembershipServiceAdapter;
import com.castle.wookpay.money.adapter.out.service.membership.response.FindMembershipResponse;
import com.castle.wookpay.money.application.port.in.IncreaseMoneyUseCase;
import com.castle.wookpay.money.application.port.in.command.IncreaseMoneyChangingCommand;
import com.castle.wookpay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class IncreaseMoneyRequestService implements IncreaseMoneyUseCase {

	private final MembershipServiceAdapter membershipServiceAdapter;

	@Override
	public MoneyChangingRequest increaseMoney(IncreaseMoneyChangingCommand command) {
		FindMembershipResponse membership = membershipServiceAdapter.findMembership(command.targetMembershipId());
		return null;
	}
}
