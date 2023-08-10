package com.castle.wookpay.membership.adapter.in.web;

import com.castle.wookpay.common.annotation.WebAdapter;
import com.castle.wookpay.common.http.ApiResponse;
import com.castle.wookpay.membership.adapter.in.web.request.RegisterMembershipRequest;
import com.castle.wookpay.membership.adapter.in.web.response.RegisterMembershipResponse;
import com.castle.wookpay.membership.application.port.in.command.RegisterMembershipCommand;
import com.castle.wookpay.membership.domain.Membership;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/membership/v1")
@RequiredArgsConstructor
public class TestController {

	@SecurityRequirement(name = "Bearer Authentication")
	@PostMapping("/test")
	public ApiResponse<String> registerMember(@Valid @RequestBody RegisterMembershipRequest request) {

		return new ApiResponse<>(
				"success",
				HttpStatus.OK
		);
	}
}
