package com.castle.wookpay.banking.application.port.out.external.bank;

import com.castle.wookpay.banking.adapter.out.external.bank.request.GetBankAccountRequest;
import com.castle.wookpay.banking.adapter.out.external.bank.response.GetBankAccountResponse;

public interface RequestBankAccountInfoPort {
	GetBankAccountResponse getBankAccountInfo(GetBankAccountRequest request);
}
