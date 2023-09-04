package com.castle.wookpay.banking.adapter.out.persistence;

import com.castle.wookpay.banking.application.port.out.FindBankingPort;
import com.castle.wookpay.banking.application.port.out.persistence.CheckDuplicateBankAccountPort;
import com.castle.wookpay.banking.domain.entity.BankAccountJpaEntity;
import com.castle.wookpay.banking.domain.repository.BankAccountRepository;
import com.castle.wookpay.common.annotation.PersistenceAdapter;
import com.castle.wookpay.common.exception.CustomException;
import com.castle.wookpay.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class CheckBankAccountAdapter implements CheckDuplicateBankAccountPort, FindBankingPort {
	private final BankAccountRepository bankAccountRepository;

	@Override
	public void existBankAccount(String bankName, String bankAccountNumber) {
		if (bankAccountRepository.existsByAndBankNameAndBankAccountNumber(bankName, bankAccountNumber)) {
			throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
		}
	}

	@Override
	public BankAccountJpaEntity findBankingAccount(String membershipId) {
		return bankAccountRepository.getBankAccountByMemberId(membershipId)
				.orElseThrow(
						() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND)
				);
	}
}
