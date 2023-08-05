package com.castle.wookpay.membership.application.service;

import com.castle.wookpay.common.annotation.UseCase;
import com.castle.wookpay.common.exception.CustomException;
import com.castle.wookpay.common.exception.ErrorCode;
import com.castle.wookpay.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.castle.wookpay.membership.application.port.in.RegisterMembershipUserCase;
import com.castle.wookpay.membership.application.port.in.command.RegisterMembershipCommand;
import com.castle.wookpay.membership.application.port.out.RegisterMembershipPort;
import com.castle.wookpay.membership.domain.Membership;
import com.castle.wookpay.membership.domain.Membership.MemberShipAddress;
import com.castle.wookpay.membership.domain.Membership.MemberShipEmail;
import com.castle.wookpay.membership.domain.Membership.MemberShipId;
import com.castle.wookpay.membership.domain.Membership.MemberShipIsCorp;
import com.castle.wookpay.membership.domain.Membership.MemberShipIsValid;
import com.castle.wookpay.membership.domain.Membership.MemberShipName;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RegisterMembershipService implements RegisterMembershipUserCase {

	private final RegisterMembershipPort registerMembershipPort;
	@Override
	public Membership registerMembership(RegisterMembershipCommand command) {
		boolean existMemberEmail = registerMembershipPort.existMemberEmail(command.email());
		if (existMemberEmail) {
			throw new CustomException(ErrorCode.USER_ALREADY_EXIST);
		}

		MembershipJpaEntity membershipJpaEntity = registerMembershipPort.registerMembership(command.name(), command.password(), command.email(), command.address());
		return Membership.generateMember(
				new MemberShipId(membershipJpaEntity.getId().toString()),
				new MemberShipName(membershipJpaEntity.getName()),
				new MemberShipEmail(membershipJpaEntity.getEmail()),
				new MemberShipAddress(membershipJpaEntity.getAddress()),
				new MemberShipIsValid(membershipJpaEntity.isValid()),
				new MemberShipIsCorp(membershipJpaEntity.isCorp())
		);
	}
}
