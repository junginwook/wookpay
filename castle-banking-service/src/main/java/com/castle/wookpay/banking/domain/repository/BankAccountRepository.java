package com.castle.wookpay.banking.domain.repository;

import com.castle.wookpay.banking.domain.entity.BankAccountJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankAccountRepository extends JpaRepository<BankAccountJpaEntity, Long> {

	@Query("SELECT b FROM BankAccountJpaEntity b WHERE b.bankName = :bankName AND b.bankAccountNumber = :bankAccountNumber")
	Optional<BankAccountJpaEntity> existsBankAccount(@Param("bankName") String bankName, @Param("bankAccountNumber") String bankAccountNumber);
}
