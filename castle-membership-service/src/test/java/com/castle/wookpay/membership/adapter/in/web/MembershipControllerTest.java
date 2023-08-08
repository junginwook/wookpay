package com.castle.wookpay.membership.adapter.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.castle.wookpay.common.http.ApiResponse;
import com.castle.wookpay.membership.adapter.in.web.request.RegisterMembershipRequest;
import com.castle.wookpay.membership.adapter.in.web.response.RegisterMembershipResponse;
import com.castle.wookpay.membership.application.port.in.RegisterMembershipUserCase;
import com.castle.wookpay.membership.config.TestSecurityConfig;
import com.castle.wookpay.membership.domain.Membership;
import com.castle.wookpay.membership.domain.Membership.MemberShipAddress;
import com.castle.wookpay.membership.domain.Membership.MemberShipEmail;
import com.castle.wookpay.membership.domain.Membership.MemberShipId;
import com.castle.wookpay.membership.domain.Membership.MemberShipIsCorp;
import com.castle.wookpay.membership.domain.Membership.MemberShipIsValid;
import com.castle.wookpay.membership.domain.Membership.MemberShipName;
import com.castle.wookpay.membership.util.FormDataEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("멤버 회원가입 controller")
@Import({FormDataEncoder.class, TestSecurityConfig.class})
@WebMvcTest(MembershipController.class)
class MembershipControllerTest {

	private final MockMvc mvc;

	@MockBean
	private RegisterMembershipUserCase registerMemberUserCase;

	MembershipControllerTest(@Autowired MockMvc mvc) {
		this.mvc = mvc;
	}

	@DisplayName("요청시 name을 빈 값으로 하면 400 상태코드 반환")
	@Test
	void givenNoName_whenRequesting_thenThrowException() throws Exception {
		RegisterMembershipRequest request = new RegisterMembershipRequest("", "pwd1", "test1@test.com", "address1");
		Membership membership = Membership.generateMember(
				new MemberShipId(UUID.randomUUID().toString()),
				new MemberShipName(request.name()),
				new MemberShipEmail(request.email()),
				new MemberShipAddress(request.address()),
				new MemberShipIsValid(true),
				new MemberShipIsCorp(false)
		);
		given(registerMemberUserCase.registerMembership(any())).willReturn(membership);

		mvc.perform(
						post("/membership/v1/member")
								.contentType(MediaType.APPLICATION_JSON)
								.content(new ObjectMapper().writeValueAsString(request))
				)
				.andExpect(status().is4xxClientError());
		then(registerMemberUserCase).shouldHaveNoInteractions();
	}

	@DisplayName("멤버 회원 가입 성공시 test")
	@Test
	void givenRegisterMemberRequest_whenRequesting_thenRegisterMembership() throws Exception {
		RegisterMembershipRequest request = new RegisterMembershipRequest("name1", "pwd1", "test1@test.com", "address1");
		Membership membership = Membership.generateMember(
				new MemberShipId(UUID.randomUUID().toString()),
				new MemberShipName(request.name()),
				new MemberShipEmail(request.email()),
				new MemberShipAddress(request.address()),
				new MemberShipIsValid(true),
				new MemberShipIsCorp(false)
		);
		given(registerMemberUserCase.registerMembership(any())).willReturn(membership);

		mvc.perform(
				post("/membership/v1/member")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(request))
		)
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().string(
						new ObjectMapper().writeValueAsString(
								new ApiResponse<>(
										new RegisterMembershipResponse(
												membership.getMemberShipId(),
												membership.getName(),
												membership.getEmail(),
												membership.getAddress()),
										HttpStatus.OK)
						)));
		then(registerMemberUserCase).should().registerMembership(any());
	}
}