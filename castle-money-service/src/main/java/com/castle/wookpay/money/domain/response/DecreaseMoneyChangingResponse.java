package com.castle.wookpay.money.domain.response;

import com.castle.wookpay.money.domain.enums.MoneyChangingResultStatus;
import com.castle.wookpay.money.domain.enums.MoneyChangingType;

public record DecreaseMoneyChangingResponse(
		String moneyChangingRequestId,
		MoneyChangingType moneyChangingType,
		MoneyChangingResultStatus moneyChangingResultStatus,
		Long amount
		) {
}
