package com.castle.wookpay.banking.application.port.out.external.bank;

import com.castle.wookpay.banking.adapter.out.external.bank.request.ExRequestFirmBankingRequest;
import com.castle.wookpay.banking.adapter.out.external.bank.response.ExRequestFirmBankingResponse;

public interface RequestExternalFirmBankingPort {
	ExRequestFirmBankingResponse requestExternalFirmBanking(ExRequestFirmBankingRequest request);
}
