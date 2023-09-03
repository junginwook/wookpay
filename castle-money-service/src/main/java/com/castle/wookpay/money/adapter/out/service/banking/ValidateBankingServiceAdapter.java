package com.castle.wookpay.money.adapter.out.service.banking;

import com.castle.wookpay.common.annotation.ExternalSystemAdapter;
import com.castle.wookpay.money.adapter.out.service.banking.response.ValidateBankingResponse;
import com.castle.wookpay.money.adapter.out.service.membership.response.ValidateMembershipResponse;
import com.castle.wookpay.money.application.port.out.service.banking.ValidateBankingPort;
import com.castle.wookpay.money.application.port.out.service.membership.ValidateMembershipPort;
import com.castle.wookpay.money.domain.feign.membership.MembershipFeignClient;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class ValidateBankingServiceAdapter implements ValidateBankingPort {

	@Override
	public void validateBanking(String membershipId, String bankName, String bankAccountNumber) {

	}
}
