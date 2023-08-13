package com.castle.wookpay.membership.adapter.in.web;

import com.castle.wookpay.common.annotation.WebAdapter;
import com.castle.wookpay.common.aop.Test;
import com.castle.wookpay.common.http.ApiResponse;
import com.castle.wookpay.membership.adapter.in.web.request.LoginMembershipRequest;
import com.castle.wookpay.membership.adapter.in.web.response.LoginMembershipResponse;
import com.castle.wookpay.membership.adapter.in.web.request.RegisterMembershipRequest;
import com.castle.wookpay.membership.adapter.in.web.response.RegisterMembershipResponse;
import com.castle.wookpay.membership.application.port.in.LoginMembershipUseCase;
import com.castle.wookpay.membership.application.port.in.RegisterMembershipUserCase;
import com.castle.wookpay.membership.application.port.in.command.LoginMembershipCommand;
import com.castle.wookpay.membership.application.port.in.command.RegisterMembershipCommand;
import com.castle.wookpay.membership.domain.Membership;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/membership/v1")
@RequiredArgsConstructor
public class MembershipController {

	private final RegisterMembershipUserCase registerMembershipUserCase;
	private final LoginMembershipUseCase findMembershipUseCase;

	@PostMapping("/member")
	public ApiResponse<RegisterMembershipResponse> registerMember(@Valid @RequestBody RegisterMembershipRequest request) {

		Membership membership = registerMembershipUserCase.registerMembership(
				new RegisterMembershipCommand(request.name(), request.password(), request.email(), request.address())
		);

		return new ApiResponse<>(
				new RegisterMembershipResponse(
						membership.getMemberShipId(),
						membership.getName(),
						membership.getEmail(),
						membership.getAddress()),
				HttpStatus.OK
		);
	}

	@PostMapping("/login")
	public ApiResponse<LoginMembershipResponse> login(@Valid @RequestBody LoginMembershipRequest request) {

		String token = findMembershipUseCase.loginMember(
				LoginMembershipCommand.builder()
						.email(request.email())
						.password(request.password())
						.build()
		);

		return new ApiResponse<>(
				new LoginMembershipResponse(token),
				HttpStatus.OK
		);
	}
}
