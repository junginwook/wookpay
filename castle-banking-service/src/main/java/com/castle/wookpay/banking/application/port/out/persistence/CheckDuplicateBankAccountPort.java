package com.castle.wookpay.banking.application.port.out.persistence;

public interface CheckDuplicateBankAccountPort {
	void existBankAccount(String bankName, String bankAccountNumber);
}
