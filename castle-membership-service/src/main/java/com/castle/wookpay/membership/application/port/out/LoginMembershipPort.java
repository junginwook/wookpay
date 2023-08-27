package com.castle.wookpay.membership.application.port.out;

import com.castle.wookpay.membership.domain.entity.MembershipJpaEntity;
import java.util.Optional;

public interface LoginMembershipPort {

	Optional<MembershipJpaEntity> findMembershipByEmail(String email);
}
