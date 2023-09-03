package com.castle.wookpay.banking.application.port.out;

public interface ValidateBankingPort {
	void validateBanking(String membershipId, String fromBankName, String fromBankAccountNumber);
}
