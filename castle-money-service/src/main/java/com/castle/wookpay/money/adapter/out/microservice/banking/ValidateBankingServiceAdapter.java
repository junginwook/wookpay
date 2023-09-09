package com.castle.wookpay.money.adapter.out.microservice.banking;

import com.castle.wookpay.common.annotation.ExternalSystemAdapter;
import com.castle.wookpay.common.http.ApiResponse;
import com.castle.wookpay.money.adapter.out.microservice.banking.response.ValidateBankingResponse;
import com.castle.wookpay.money.adapter.out.microservice.membership.response.ValidateMembershipResponse;
import com.castle.wookpay.money.application.port.out.service.banking.ValidateBankingPort;
import com.castle.wookpay.money.domain.feign.banking.BankingFeignClient;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class ValidateBankingServiceAdapter implements ValidateBankingPort {
	private final BankingFeignClient bankingFeignClient;
	@Override
	public ValidateBankingResponse validateBanking(String membershipId) {
		return bankingFeignClient.validateBankingAccount(membershipId).getResult();
	}
}
