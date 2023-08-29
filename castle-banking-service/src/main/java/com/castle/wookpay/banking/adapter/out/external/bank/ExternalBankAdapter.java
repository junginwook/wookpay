package com.castle.wookpay.banking.adapter.out.external.bank;

import com.castle.wookpay.banking.adapter.out.external.bank.request.ExGetBankAccountRequest;
import com.castle.wookpay.banking.adapter.out.external.bank.request.ExRequestFirmBankingRequest;
import com.castle.wookpay.banking.adapter.out.external.bank.response.ExGetBankAccountResponse;
import com.castle.wookpay.banking.adapter.out.external.bank.response.ExRequestFirmBankingResponse;
import com.castle.wookpay.banking.application.port.out.external.bank.RequestBankAccountInfoPort;
import com.castle.wookpay.banking.application.port.out.external.bank.RequestExternalFirmBankingPort;
import com.castle.wookpay.common.annotation.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class ExternalBankAdapter implements RequestBankAccountInfoPort, RequestExternalFirmBankingPort {

	@Override
	public ExGetBankAccountResponse getBankAccountInfo(ExGetBankAccountRequest request) {
		return new ExGetBankAccountResponse("bankName", "1234", true);
	}

	@Override
	public ExRequestFirmBankingResponse requestExternalFirmBanking(ExRequestFirmBankingRequest request) {
		return new ExRequestFirmBankingResponse(0);
	}
}
