package com.castle.wookpay.membership.config;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.castle.wookpay.common.security.JwtAuthorizationFilter;
import com.castle.wookpay.common.security.JwtTokenProvider;
import com.castle.wookpay.common.security.dto.MemberDto;
import com.castle.wookpay.membership.SecurityConfig;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

@Import({SecurityConfig.class, TestUserDetailsService.class, JwtAuthorizationFilter.class, JwtTokenProvider.class})
public class TestSecurityConfig {

	@MockBean
	private TestUserDetailsService testUserDetailsService;

	@BeforeTestMethod
	public void securitySetUp() {
		given(testUserDetailsService.loadUserByUsername(anyString()))
				.willReturn(createUserAccountDto());
	}

	private MemberDto createUserAccountDto() {
		return new MemberDto("1", "inwook94@naver.com");
	}
}
