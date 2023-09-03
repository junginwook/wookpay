package com.castle.wookpay.banking.adapter.out.persistence;

import com.castle.wookpay.banking.application.port.out.persistence.RegisterBankAccountPort;
import com.castle.wookpay.banking.domain.entity.BankAccountJpaEntity;
import com.castle.wookpay.banking.domain.repository.BankAccountRepository;
import com.castle.wookpay.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class RegisterBankAccountAdapter implements RegisterBankAccountPort {
	private final BankAccountRepository bankAccountRepository;

	@Override
	public BankAccountJpaEntity registerBankAccount(String membershipId, String bankName, String bankAccountNumber) {
		return bankAccountRepository.save(
				new BankAccountJpaEntity(
						membershipId,
						bankName,
						bankAccountNumber
				)
		);
	}
}
