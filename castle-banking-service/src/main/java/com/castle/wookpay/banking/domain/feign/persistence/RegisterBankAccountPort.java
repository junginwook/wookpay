package com.castle.wookpay.banking.domain.feign.persistence;

import com.castle.wookpay.banking.domain.entity.BankAccountJpaEntity;

public interface RegisterBankAccountPort {
	BankAccountJpaEntity registerBankAccount(String membershipId, String bankName, String bankAccountNumber);
}
