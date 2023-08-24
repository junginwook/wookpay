package com.castle.wookpay.money.application.port.out.service.banking;

import com.castle.wookpay.money.adapter.out.service.banking.response.ValidateBankingResponse;
import com.castle.wookpay.money.adapter.out.service.membership.response.ValidateMembershipResponse;

public interface ValidateBankingPort {

	ValidateBankingResponse validateBanking(String membershipId);
}
