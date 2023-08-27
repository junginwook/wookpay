package com.castle.wookpay.banking.application.port.out.external.bank;

import com.castle.wookpay.banking.adapter.out.external.bank.request.RequestFirmBankingRequest;
import com.castle.wookpay.banking.adapter.out.external.bank.response.RequestFirmBankingResponse;

public interface RequestExternalFirmBankingPort {
	RequestFirmBankingResponse requestExternalFirmBanking(RequestFirmBankingRequest request);
}
