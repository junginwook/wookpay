package com.castle.wookpay.membership.application.port.in;

import com.castle.wookpay.membership.application.port.in.command.RegisterMembershipCommand;
import com.castle.wookpay.membership.domain.Membership;

public interface RegisterMemberUserCase {
	Membership registerMembership(RegisterMembershipCommand command);
}
