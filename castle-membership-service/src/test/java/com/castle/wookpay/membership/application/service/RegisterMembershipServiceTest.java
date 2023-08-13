package com.castle.wookpay.membership.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.castle.wookpay.common.exception.CustomException;
import com.castle.wookpay.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.castle.wookpay.membership.application.port.in.command.RegisterMembershipCommand;
import com.castle.wookpay.membership.application.port.out.RegisterMembershipPort;
import com.castle.wookpay.membership.domain.Membership;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("멤버 회원가입 service")
@ExtendWith(MockitoExtension.class)
class RegisterMembershipServiceTest {

	@InjectMocks
	private RegisterMembershipService sut;
	@Mock
	private RegisterMembershipPort registerMembershipPort;

	@DisplayName("중복 이메일 등록로 가입 요청시 Throw Exception")
	@Test
	void givenDuplicatedEmail_whenRegisterMembership_thenThrowCustomException() {
		//given
		RegisterMembershipCommand command = new RegisterMembershipCommand("test1", "password1", "email", "address1");
		given(registerMembershipPort.existMemberEmail(any())).willReturn(true);

		//when
		Throwable throwable = catchThrowable(() -> sut.registerMembership(command));

		//then
		assertThat(throwable)
				.isInstanceOf(CustomException.class);
		then(registerMembershipPort).should().existMemberEmail(command.email());
		then(registerMembershipPort).shouldHaveNoMoreInteractions();
	}

	@DisplayName("중복 없는 이메일로 가입 요청시 정상 등록")
	@Test
	void givenNonDuplicatedEmail_whenRegisterMembership_thenRegisterSuccess() {
		//given
		RegisterMembershipCommand command = new RegisterMembershipCommand("test1", "password1", "email", "address1");
		given(registerMembershipPort.existMemberEmail(any())).willReturn(false);
		given(registerMembershipPort.registerMembership(any(), any(), any(), any()))
				.willReturn(MembershipJpaEntity.of(command.name(), command.email(), null, command.address(), true, false));

		//when
		Membership membership = sut.registerMembership(command);

		//then
		then(registerMembershipPort).should().existMemberEmail(command.email());
		then(registerMembershipPort).should().registerMembership(command.name(), command.password(), command.email(), command.address());
		assertThat(membership.getName()).isEqualTo(command.name());
		assertThat(membership.getEmail()).isEqualTo(command.email());
	}
}