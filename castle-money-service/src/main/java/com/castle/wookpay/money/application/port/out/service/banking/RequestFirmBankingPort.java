package com.castle.wookpay.money.application.port.out.service.banking;

import com.castle.wookpay.money.adapter.out.microservice.banking.request.RequestFirmBankingRequest;
import com.castle.wookpay.money.adapter.out.microservice.banking.response.RequestFirmBankingResponse;

public interface RequestFirmBankingPort {
	RequestFirmBankingResponse requestFirmBanking(RequestFirmBankingRequest request);
}
