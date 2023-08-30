package com.castle.wookpay.banking.domain.repository;

import com.castle.wookpay.banking.domain.entity.FirmBankingRequestJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmBankingRequestRepository extends JpaRepository<FirmBankingRequestJpaEntity, Long> {

}
