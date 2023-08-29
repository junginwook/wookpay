package com.castle.wookpay.banking.application.port.out.external.bank;

import com.castle.wookpay.banking.adapter.out.external.bank.request.ExGetBankAccountRequest;
import com.castle.wookpay.banking.adapter.out.external.bank.response.ExGetBankAccountResponse;

public interface RequestBankAccountInfoPort {
	ExGetBankAccountResponse getBankAccountInfo(ExGetBankAccountRequest request);
}
