package com.castle.wookpay.money.application.port.in;

import com.castle.wookpay.money.application.port.in.command.IncreaseMoneyChangingCommand;
import com.castle.wookpay.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyUseCase {
	MoneyChangingRequest increaseMoney(IncreaseMoneyChangingCommand command);
}
