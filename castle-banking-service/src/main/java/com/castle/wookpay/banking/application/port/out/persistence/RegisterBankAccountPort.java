package com.castle.wookpay.banking.application.port.out.persistence;

public interface RegisterBankAccountPort {
	void registerBankAccount(String membershipId, String bankName);
}
