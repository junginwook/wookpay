package com.castle.wookpay.membership.application.service;

import static org.junit.jupiter.api.Assertions.*;

import com.castle.wookpay.membership.application.port.out.RegisterMembershipPort;
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

	}

	@DisplayName("중복 없는 이메일로 가입 요청시 정상 등록")
	@Test
	void givenNonDuplicatedEmail_whenRegisterMembership_thenRegisterSuccess() {

	}
}