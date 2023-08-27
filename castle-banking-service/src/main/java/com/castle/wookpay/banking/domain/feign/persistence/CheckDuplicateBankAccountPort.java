package com.castle.wookpay.banking.domain.feign.persistence;

import com.castle.wookpay.banking.domain.entity.BankAccountJpaEntity;
import java.util.Optional;

public interface CheckDuplicateBankAccountPort {
	Optional<BankAccountJpaEntity> existBankAccount(String bankName, String bankAccountNumber);
}
