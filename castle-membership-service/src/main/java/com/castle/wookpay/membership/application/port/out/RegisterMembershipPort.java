package com.castle.wookpay.membership.application.port.out;

import com.castle.wookpay.membership.domain.entity.MembershipJpaEntity;

public interface RegisterMembershipPort {
	boolean existMemberEmail(String email);
	MembershipJpaEntity registerMembership(String name, String password, String email, String address);
}
