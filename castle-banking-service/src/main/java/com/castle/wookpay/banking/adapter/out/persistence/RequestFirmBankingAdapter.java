package com.castle.wookpay.banking.adapter.out.persistence;

import com.castle.wookpay.banking.application.port.out.RequestFirmBankingPort;
import com.castle.wookpay.banking.domain.FirmBankingRequest;
import com.castle.wookpay.banking.domain.entity.FirmBankingRequestJpaEntity;
import com.castle.wookpay.banking.domain.enums.FirmBankingStatus;
import com.castle.wookpay.banking.domain.repository.FirmBankingRequestRepository;
import com.castle.wookpay.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class RequestFirmBankingAdapter implements RequestFirmBankingPort {

	private final FirmBankingRequestRepository firmBankingRequestRepository;

	@Override
	public FirmBankingRequestJpaEntity createFirmBankingRequest(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, Long moneyAmount,
			FirmBankingStatus firmBankingStatus) {
		return firmBankingRequestRepository.save(
				new FirmBankingRequestJpaEntity(
					fromBankName,
					fromBankAccountNumber,
					toBankName,
					toBankAccountNumber,
					moneyAmount,
					firmBankingStatus
				)
		);
	}
}
