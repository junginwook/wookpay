package com.castle.wookpay.membership.application.service;

import com.castle.wookpay.common.annotation.UseCase;
import com.castle.wookpay.common.exception.CustomException;
import com.castle.wookpay.common.exception.ErrorCode;
import com.castle.wookpay.common.security.JwtTokenProvider;
import com.castle.wookpay.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.castle.wookpay.membership.application.port.in.LoginMembershipUseCase;
import com.castle.wookpay.membership.application.port.in.command.LoginMembershipCommand;
import com.castle.wookpay.membership.application.port.out.LoginMembershipPort;
import com.castle.wookpay.common.security.dto.MemberDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class LoginMembershipService implements LoginMembershipUseCase {

	private final PasswordEncoder passwordEncoder;
	private final LoginMembershipPort loginMembershipPort;
	private final JwtTokenProvider jwtTokenProvider;
	@Override
	public String loginMember(LoginMembershipCommand command) {

		MembershipJpaEntity membershipJpaEntity = validateEmail(command);
		validatePassword(command.password(), membershipJpaEntity.getPassword());

		return jwtTokenProvider.generateJwtToken(
				new MemberDto(String.valueOf(membershipJpaEntity.getId()), membershipJpaEntity.getEmail())
		);
	}

	private MembershipJpaEntity validateEmail(LoginMembershipCommand command) {
		Optional<MembershipJpaEntity> optionalMembershipEntity = loginMembershipPort.findMembershipByEmail(command.email());
		MembershipJpaEntity membershipJpaEntity = optionalMembershipEntity.orElseThrow(() -> {
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		});
		return membershipJpaEntity;
	}

	private void validatePassword(String rawPassword, String encodedPassword) {
		boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
		if (!matches) {
			throw new CustomException(ErrorCode.INVALID_ACCOUNT);
		}
	}
}
