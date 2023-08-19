package com.castle.wookpay.money.adapter.in.web.response;

import com.castle.wookpay.money.domain.enums.MoneyChangingResultStatus;
import com.castle.wookpay.money.domain.enums.MoneyChangingType;

public record IncreaseMoneyChangingResponse(
		String moneyChangingRequestId,
		MoneyChangingType moneyChangingType,
		MoneyChangingResultStatus moneyChangingResultStatus,
		Long amount
		) {
}
