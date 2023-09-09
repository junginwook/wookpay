package com.castle.wookpay.money.application.port.in;

import com.castle.wookpay.money.domain.command.CreateMemberMoneyCommand;

public interface CreateMemberMoneyUseCase {

	void createMemberMoney(CreateMemberMoneyCommand command);
}
