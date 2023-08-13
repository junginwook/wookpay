package com.castle.wookpay.membership.application.port.in;

import com.castle.wookpay.membership.application.port.in.command.FindMembershipCommand;
import com.castle.wookpay.membership.domain.Membership;

public interface FindMembershipUseCase {
	Membership findMember(FindMembershipCommand command);
}
