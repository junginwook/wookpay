package com.castle.wookpay.membership.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.castle.wookpay.common.exception.CustomException;
import com.castle.wookpay.common.security.JwtTokenProvider;
import com.castle.wookpay.membership.application.port.in.command.LoginMembershipCommand;
import com.castle.wookpay.membership.application.port.out.LoginMembershipPort;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

@DisplayName("멤버 로그인 service")
@ExtendWith(MockitoExtension.class)
class LoginMembershipServiceTest {

	@InjectMocks
	private LoginMembershipService sut;
	@Mock
	private LoginMembershipPort loginMembershipPort;
	@Mock
	private JwtTokenProvider jwtTokenProvider;

	@DisplayName("아이디가 없는 경우 테스트")
	@Test
	void givenIncorrectEmail_whenLogin_thenCustomException() {
		LoginMembershipCommand command = LoginMembershipCommand.builder()
				.email("test@test.com")
				.password("test1234")
				.build();
		given(loginMembershipPort.findMembershipByEmail(command.email())).willReturn(Optional.empty());

		Throwable throwable = catchThrowable(() -> sut.loginMember(command));

		assertThat(throwable)
				.isInstanceOf(CustomException.class)
				.hasMessage("user not found");
		then(jwtTokenProvider).shouldHaveNoInteractions();
	}

	@DisplayName("비밀번호가 불일치 하는 경우 테스트")
	@Test
	void givenIncorrectPassword_whenLogin_thenCustomException() {

	}

	@DisplayName("아이디 및 비밀번호 모두 일치 테스트")
	@Test
	void givenCorrectLoginRequest_whenLogin_thenReturnToken() {

	}
}