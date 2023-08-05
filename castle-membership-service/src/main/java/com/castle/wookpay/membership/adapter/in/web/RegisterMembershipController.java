package com.castle.wookpay.membership.adapter.in.web;

import com.castle.wookpay.common.annotations.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/membership/v1")
@RequiredArgsConstructor
public class RegisterMemberController {

	@PostMapping("/member")
	public
}
