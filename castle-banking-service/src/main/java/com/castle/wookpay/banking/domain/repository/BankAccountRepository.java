package com.castle.wookpay.banking.domain.repository;

import com.castle.wookpay.banking.domain.entity.BankAccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccountJpaEntity, Long> {

	boolean existsByAndBankNameAndBankAccountNumber(String bankName, String bankAccountNumber);

	boolean existsByMemberShipIdAndBankNameAndBankAccountNumber(String membershipId, String bankName, String bankAccountNumber);
}
