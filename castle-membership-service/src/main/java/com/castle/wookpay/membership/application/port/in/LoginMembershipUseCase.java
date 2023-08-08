package com.castle.wookpay.membership.application.port.in;

import com.castle.wookpay.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.castle.wookpay.membership.application.port.in.command.LoginMembershipCommand;
import java.util.Optional;

public interface LoginMembershipUseCase {
	String loginMember(LoginMembershipCommand command);
}
