package com.castle.wookpay.money.adapter.out.persistence;

import com.castle.wookpay.common.annotation.PersistenceAdapter;
import com.castle.wookpay.money.application.port.out.persistence.CreateMemberMoneyPort;
import com.castle.wookpay.money.application.port.out.persistence.GetMemberMoneyPort;
import com.castle.wookpay.money.domain.entity.MemberMoneyJpaEntity;
import com.castle.wookpay.money.domain.repository.MemberMoneyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CreateMemberMoneyAdapter implements CreateMemberMoneyPort, GetMemberMoneyPort {

	private final MemberMoneyRepository memberMoneyRepository;

	@Override
	public void createMemberMoney(String membershipId, String moneyAggregateIdentifier) {
		MemberMoneyJpaEntity entity = new MemberMoneyJpaEntity(
				Long.parseLong(membershipId),
				0,
				moneyAggregateIdentifier
		);
		memberMoneyRepository.save(entity);
	}

	@Override
	public MemberMoneyJpaEntity getMemberMoney(String membershipId) {
		List<MemberMoneyJpaEntity> entityList = memberMoneyRepository.findByMembershipId(Long.parseLong(membershipId));

		return entityList.get(0);
	}
}
