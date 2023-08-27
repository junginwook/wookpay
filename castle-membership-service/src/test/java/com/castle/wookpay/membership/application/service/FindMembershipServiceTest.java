package com.castle.wookpay.membership.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.castle.wookpay.common.exception.CustomException;
import com.castle.wookpay.membership.application.port.in.command.FindMembershipCommand;
import com.castle.wookpay.membership.application.port.out.ValidateMembershipPort;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("멤버 조회 service")
@ExtendWith(MockitoExtension.class)
class FindMembershipServiceTest {

	@InjectMocks
	private ValidateMembershipService sut;
	@Mock
	private ValidateMembershipPort findMembershipPort;

	@DisplayName("멤버 조회시 없을 경우 Exception")
	@Test
	void givenNonExistMemberId_whenFindMember_thenException() {
		//given
		FindMembershipCommand command = new FindMembershipCommand("1");
		given(findMembershipPort.findMembershipById(any())).willReturn(Optional.empty());

		//when
		Throwable throwable = catchThrowable(() -> sut.findMember(command));

		//then
		assertThat(throwable).isInstanceOf(CustomException.class)
				.hasMessage("user not found");
		then(findMembershipPort).should().findMembershipById(command.memberId());
	}

	@DisplayName("멤버 조회시 유효하지 않을 경우 Exception")
	@Test
	void givenInvalidMemberId_whenFindMember_thenException() {
		//given

		//when

		//then
	}
}