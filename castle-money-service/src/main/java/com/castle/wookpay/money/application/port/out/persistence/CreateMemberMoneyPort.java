package com.castle.wookpay.money.application.port.out.persistence;

public interface CreateMemberMoneyPort {
	void createMemberMoney(
			String membershipId,
			String moneyAggregateIdentifier
	);
}
