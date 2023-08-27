package com.castle.wookpay.membership.application.port.in;

import com.castle.wookpay.membership.application.port.in.command.LoginMembershipCommand;

public interface LoginMembershipUseCase {
	String loginMember(LoginMembershipCommand command);
}
