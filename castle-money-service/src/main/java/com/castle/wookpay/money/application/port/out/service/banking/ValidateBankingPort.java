package com.castle.wookpay.money.application.port.out.service.banking;

import com.castle.wookpay.money.adapter.out.microservice.banking.response.ValidateBankingResponse;

public interface ValidateBankingPort {
	ValidateBankingResponse validateBanking(String membershipId);
}
