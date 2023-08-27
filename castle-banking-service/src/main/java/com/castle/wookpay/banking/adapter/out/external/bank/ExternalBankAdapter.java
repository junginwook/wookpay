package com.castle.wookpay.banking.adapter.out.external.bank;

import com.castle.wookpay.banking.adapter.out.external.bank.request.GetBankAccountRequest;
import com.castle.wookpay.banking.adapter.out.external.bank.request.RequestFirmBankingRequest;
import com.castle.wookpay.banking.adapter.out.external.bank.response.GetBankAccountResponse;
import com.castle.wookpay.banking.adapter.out.external.bank.response.RequestFirmBankingResponse;
import com.castle.wookpay.banking.application.port.out.external.bank.RequestBankAccountInfoPort;
import com.castle.wookpay.banking.application.port.out.external.bank.RequestExternalFirmBankingPort;
import com.castle.wookpay.common.annotation.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class ExternalBankAdapter implements RequestBankAccountInfoPort, RequestExternalFirmBankingPort {

	@Override
	public GetBankAccountResponse getBankAccountInfo(GetBankAccountRequest request) {
		return new GetBankAccountResponse("bankName", "1234", true);
	}

	@Override
	public RequestFirmBankingResponse requestExternalFirmBanking(RequestFirmBankingRequest request) {
		return new RequestFirmBankingResponse(0);
	}
}
