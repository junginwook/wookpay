package com.castle.wookpay.membership.application.service;

import com.castle.wookpay.common.annotation.UseCase;
import com.castle.wookpay.common.exception.CustomException;
import com.castle.wookpay.common.exception.ErrorCode;
import com.castle.wookpay.membership.domain.entity.MembershipJpaEntity;
import com.castle.wookpay.membership.application.port.in.ValidateMembershipUseCase;
import com.castle.wookpay.membership.application.port.in.command.FindMembershipCommand;
import com.castle.wookpay.membership.application.port.out.ValidateMembershipPort;
import com.castle.wookpay.membership.domain.Membership;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class ValidateMembershipService implements ValidateMembershipUseCase {

	private final ValidateMembershipPort findMembershipPort;

	@Override
	public Membership validateMember(FindMembershipCommand command) {
		Optional<MembershipJpaEntity> optionalMembershipEntity = findMembershipPort.findMembershipById(command.memberId());
		MembershipJpaEntity membershipJpaEntity = optionalMembershipEntity.orElseThrow(
				() -> new CustomException(ErrorCode.USER_NOT_FOUND)
		);

		Membership membership = Membership.generateMember(membershipJpaEntity);
		if (!membership.isValid()) {
			throw new CustomException(ErrorCode.USER_NOT_VALID);
		}

		return membership;
	}
}
