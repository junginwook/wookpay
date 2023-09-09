package com.castle.wookpay.money.domain.feign.banking;

import com.castle.wookpay.common.http.ApiResponse;
import com.castle.wookpay.money.adapter.out.microservice.banking.request.RequestFirmBankingRequest;
import com.castle.wookpay.money.adapter.out.microservice.banking.response.RequestFirmBankingResponse;
import com.castle.wookpay.money.adapter.out.microservice.banking.response.ValidateBankingResponse;
import com.castle.wookpay.money.adapter.out.microservice.membership.response.ValidateMembershipResponse;
import com.castle.wookpay.money.domain.feign.membership.MembershipFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
		name = "banking-client",
		url = "${feign.url.banking}",
		configuration = MembershipFeignConfig.class
)
public interface BankingFeignClient {
	@GetMapping("/internal/v1/account/{memberId}")
	ApiResponse<ValidateBankingResponse> validateBankingAccount(
			@PathVariable("memberId") String memberId
	);

	@PostMapping("/internal/v1/firmBanking")
	ApiResponse<RequestFirmBankingResponse> requestFirmBanking(
			@RequestBody RequestFirmBankingRequest request
	);
}
