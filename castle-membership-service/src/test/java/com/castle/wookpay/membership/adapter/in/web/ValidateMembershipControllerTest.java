package com.castle.wookpay.membership.adapter.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.castle.wookpay.membership.application.port.in.command.FindMembershipCommand;
import com.castle.wookpay.membership.application.service.ValidateMembershipService;
import com.castle.wookpay.membership.config.TestSecurityConfig;
import com.castle.wookpay.membership.domain.Membership;
import com.castle.wookpay.membership.domain.Membership.MemberShipAddress;
import com.castle.wookpay.membership.domain.Membership.MemberShipEmail;
import com.castle.wookpay.membership.domain.Membership.MemberShipId;
import com.castle.wookpay.membership.domain.Membership.MemberShipIsCorp;
import com.castle.wookpay.membership.domain.Membership.MemberShipIsValid;
import com.castle.wookpay.membership.domain.Membership.MemberShipName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("유효한 멤버 조회 controller")
@Import({TestSecurityConfig.class})
@WebMvcTest(ValidateMembershipController.class)
class ValidateMembershipControllerTest {

	private final MockMvc mvc;
	@MockBean
	private ValidateMembershipService findMembershipService;

	public ValidateMembershipControllerTest(@Autowired MockMvc mvc) {
		this.mvc = mvc;
	}

	@DisplayName("멤버 조회 성공")
	@WithUserDetails(setupBefore = TestExecutionEvent.TEST_EXECUTION)
	@Test
	void givenMemberId_whenFindMember_thenResponse() throws Exception {
		final String memberId = "1";
		Membership membership = Membership.generateMember(new MemberShipId(memberId),
				new MemberShipName("name"),
				new MemberShipEmail("email"),
				new MemberShipAddress("address"),
				new MemberShipIsValid(true),
				new MemberShipIsCorp(false)
		);
		given(findMembershipService.findMember(any())).willReturn(membership);

		mvc.perform(
						get("/membership/v1/member/" + memberId)
								.contentType(MediaType.APPLICATION_JSON)

				)
				.andExpect(status().is2xxSuccessful());
		then(findMembershipService).should().findMember(new FindMembershipCommand(memberId));
	}
}