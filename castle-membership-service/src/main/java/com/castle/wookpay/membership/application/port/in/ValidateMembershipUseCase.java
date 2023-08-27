package com.castle.wookpay.membership.application.port.in;

import com.castle.wookpay.membership.application.port.in.command.FindMembershipCommand;
import com.castle.wookpay.membership.domain.Membership;

public interface ValidateMembershipUseCase {
	Membership validateMember(FindMembershipCommand command);
}
