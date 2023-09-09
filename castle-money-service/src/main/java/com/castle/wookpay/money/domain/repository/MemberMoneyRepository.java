package com.castle.wookpay.money.domain.repository;

import com.castle.wookpay.money.domain.entity.MemberMoneyJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity, Long> {

	List<MemberMoneyJpaEntity> findByMembershipId(long membershipId);
}
