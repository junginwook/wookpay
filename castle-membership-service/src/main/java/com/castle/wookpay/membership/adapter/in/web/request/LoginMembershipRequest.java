package com.castle.wookpay.membership.adapter.in.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

public record LoginMembershipRequest(@Email @NotNull @NotEmpty @Schema(description = "이메일", defaultValue = "inwook94@naver.com") String email,
                                     @NotNull @NotEmpty @Schema(description = "비밀번호", defaultValue = "1234") String password) {

	@Builder
	public LoginMembershipRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}
}

