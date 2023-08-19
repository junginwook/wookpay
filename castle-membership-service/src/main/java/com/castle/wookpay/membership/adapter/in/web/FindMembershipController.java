package com.castle.wookpay.membership.adapter.in.web;

import com.castle.wookpay.common.annotation.WebAdapter;
import com.castle.wookpay.common.http.ApiResponse;
import com.castle.wookpay.common.security.dto.MemberDto;
import com.castle.wookpay.membership.adapter.in.web.response.FindMembershipResponse;
import com.castle.wookpay.membership.application.port.in.FindMembershipUseCase;
import com.castle.wookpay.membership.application.port.in.command.FindMembershipCommand;
import com.castle.wookpay.membership.domain.Membership;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/membership/v1")
@RequiredArgsConstructor
public class FindMembershipController {

	private final FindMembershipUseCase findMembershipUseCase;

	@Operation(summary = "유저 정보 조회")
	@GetMapping("/member/{memberId}")
	public ApiResponse<FindMembershipResponse> findValidMember(@PathVariable("memberId") String memberId) {

		Membership membership = findMembershipUseCase.findMember(
				new FindMembershipCommand(memberId)
		);

		return new ApiResponse<>(
				new FindMembershipResponse(membership.getEmail(), membership.getName(), membership.getMemberShipId()),
				HttpStatus.OK
		);
	}
}
