package com.castle.wookpay.banking.domain.repository;

import com.castle.wookpay.banking.domain.entity.BankAccountJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankAccountRepository extends JpaRepository<BankAccountJpaEntity, Long> {

	boolean existsByAndBankNameAndBankAccountNumber(String bankName, String bankAccountNumber);

	@Query("SELECT b FROM BankAccountJpaEntity b WHERE b.memberShipId = :membershipId")
	Optional<BankAccountJpaEntity> getBankAccountByMemberId(@Param("membershipId") String membershipId);
}
