package com.castle.wookpay.banking.application.port.out.persistence;

import com.castle.wookpay.banking.domain.entity.BankAccountJpaEntity;

public interface RegisterBankAccountPort {
	BankAccountJpaEntity registerBankAccount(String membershipId, String bankName, String bankAccountNumber);
}
