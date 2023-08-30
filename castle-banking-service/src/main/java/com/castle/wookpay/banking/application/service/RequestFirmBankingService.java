package com.castle.wookpay.banking.application.service;

import com.castle.wookpay.banking.adapter.out.external.bank.request.ExRequestFirmBankingRequest;
import com.castle.wookpay.banking.adapter.out.external.bank.response.ExRequestFirmBankingResponse;
import com.castle.wookpay.banking.application.port.in.RequestFirmBankingUseCase;
import com.castle.wookpay.banking.application.port.out.RequestFirmBankingPort;
import com.castle.wookpay.banking.application.port.out.external.bank.RequestExternalFirmBankingPort;
import com.castle.wookpay.banking.domain.command.RequestFirmBankingCommand;
import com.castle.wookpay.banking.domain.entity.FirmBankingRequestJpaEntity;
import com.castle.wookpay.banking.domain.enums.FirmBankingStatus;
import com.castle.wookpay.banking.domain.result.RequestFirmBankingResult;
import com.castle.wookpay.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmBankingService implements RequestFirmBankingUseCase {

	private final RequestFirmBankingPort requestFirmBankingPort;
	private final RequestExternalFirmBankingPort requestExternalFirmBankingPort;

	@Override
	public RequestFirmBankingResult requestFirmBanking(RequestFirmBankingCommand command) {
		// 펌뱅킹 요청 저장
		final FirmBankingRequestJpaEntity firmBankingRequest = requestFirmBankingPort.createFirmBankingRequest(
				command.fromBankName(),
				command.fromBankAccountNumber(),
				command.toBankName(),
				command.toBankAccountNumber(),
				command.moneyAmount(),
				FirmBankingStatus.BEFORE
		);

		// 외부 은행에 펌뱅킹 요청
		final ExRequestFirmBankingResponse exRequestFirmBankingResponse = requestExternalFirmBankingPort.requestExternalFirmBanking(new ExRequestFirmBankingRequest(
				command.fromBankName(),
				command.fromBankAccountNumber(),
				command.toBankName(),
				command.toBankAccountNumber()
		));

		//외부 은행의 요청 상태에 따라 상태 변경
		changeFirmBankingRequestStatus(firmBankingRequest, exRequestFirmBankingResponse);

		return new RequestFirmBankingResult(
				firmBankingRequest.getId().toString(),
				firmBankingRequest.getFromBankName(),
				firmBankingRequest.getFromBankAccountNumber(),
				firmBankingRequest.getToBankName(),
				firmBankingRequest.getFromBankAccountNumber(),
				firmBankingRequest.getMoneyAmount()
		);
	}

	private void changeFirmBankingRequestStatus(FirmBankingRequestJpaEntity firmBankingRequest, ExRequestFirmBankingResponse exRequestFirmBankingResponse) {
		if (exRequestFirmBankingResponse.resultCode() == 0) {
			firmBankingRequest.changeStatus(FirmBankingStatus.SUCCESS);
		} else {
			firmBankingRequest.changeStatus(FirmBankingStatus.FAIL);
		}
	}
}
