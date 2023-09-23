package com.castle.wookpay.money.adapter.out.microservice.banking;

import com.castle.wookpay.common.annotation.ExternalSystemAdapter;
import com.castle.wookpay.money.adapter.out.microservice.banking.request.RequestFirmBankingRequest;
import com.castle.wookpay.money.adapter.out.microservice.banking.response.RequestFirmBankingResponse;
import com.castle.wookpay.money.application.port.out.service.banking.RequestFirmBankingPort;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class RequestFirmBankingAdapter implements RequestFirmBankingPort {

	@Override
	public RequestFirmBankingResponse requestFirmBanking(RequestFirmBankingRequest request) {
		return null;
	}
}
