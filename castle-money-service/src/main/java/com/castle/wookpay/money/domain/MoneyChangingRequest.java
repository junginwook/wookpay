package com.castle.wookpay.money.domain;

import com.castle.wookpay.money.domain.enums.MoneyChangingResultStatus;
import com.castle.wookpay.money.domain.enums.MoneyChangingType;
import lombok.Getter;

public class MoneyChangingRequest {
	@Getter private final String moneyChangingRequestId;

	@Getter private final String targetMembershipId;

	@Getter private final MoneyChangingType changingType;

	@Getter private final Long changingMoneyAmount;

	@Getter private final MoneyChangingResultStatus changingMoneyResultStatus;

	@Getter private final boolean uuid;

	public MoneyChangingRequest(String moneyChangingRequestId, String targetMembershipId, MoneyChangingType changingType, Long changingMoneyAmount, MoneyChangingResultStatus changingMoneyResultStatus,
			boolean uuid) {
		this.moneyChangingRequestId = moneyChangingRequestId;
		this.targetMembershipId = targetMembershipId;
		this.changingType = changingType;
		this.changingMoneyAmount = changingMoneyAmount;
		this.changingMoneyResultStatus = changingMoneyResultStatus;
		this.uuid = uuid;
	}
}
