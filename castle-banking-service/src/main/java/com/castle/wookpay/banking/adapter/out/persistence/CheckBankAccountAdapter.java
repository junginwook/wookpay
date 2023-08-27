package com.castle.wookpay.banking.adapter.out.persistence;

import com.castle.wookpay.banking.domain.feign.persistence.CheckDuplicateBankAccountPort;
import com.castle.wookpay.banking.domain.entity.BankAccountJpaEntity;
import com.castle.wookpay.banking.domain.repository.BankAccountRepository;
import com.castle.wookpay.common.annotation.PersistenceAdapter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class CheckBankAccountAdapter implements CheckDuplicateBankAccountPort {
	private final BankAccountRepository bankAccountRepository;

	@Override
	public Optional<BankAccountJpaEntity> existBankAccount(String bankName, String bankAccountNumber) {
		return bankAccountRepository.existsBankAccount(bankName, bankAccountNumber);
	}
}
