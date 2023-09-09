package com.castle.wookpay.money.application.port.out.persistence;

import com.castle.wookpay.money.domain.entity.MemberMoneyJpaEntity;

public interface GetMemberMoneyPort {
	MemberMoneyJpaEntity getMemberMoney(String membershipId);

}
