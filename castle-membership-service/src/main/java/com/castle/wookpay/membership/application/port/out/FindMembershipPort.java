package com.castle.wookpay.membership.application.port.out;

import com.castle.wookpay.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import java.util.Optional;

public interface FindMembershipPort {
	Optional<MembershipJpaEntity> findMembershipById(String memberId);
}
