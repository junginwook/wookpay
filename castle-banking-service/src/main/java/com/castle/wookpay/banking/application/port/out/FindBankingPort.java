package com.castle.wookpay.banking.application.port.out;

import com.castle.wookpay.banking.domain.entity.BankAccountJpaEntity;

public interface FindBankingPort {
	BankAccountJpaEntity findBankingAccount(String membershipId);
}
