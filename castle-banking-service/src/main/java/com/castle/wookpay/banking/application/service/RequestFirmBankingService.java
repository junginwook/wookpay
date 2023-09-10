package com.castle.wookpay.banking.application.service;

import com.castle.wookpay.banking.adapter.axon.command.CreateFrimbankingRequestCommand;
import com.castle.wookpay.banking.adapter.axon.command.UpdateFirmBankingRequestCommand;
import com.castle.wookpay.banking.adapter.out.external.bank.request.ExRequestFirmBankingRequest;
import com.castle.wookpay.banking.adapter.out.external.bank.response.ExRequestFirmBankingResponse;
import com.castle.wookpay.banking.application.port.in.RequestFirmBankingUseCase;
import com.castle.wookpay.banking.application.port.in.UpdateFirmBankingUseCase;
import com.castle.wookpay.banking.application.port.out.RequestFirmBankingPort;
import com.castle.wookpay.banking.application.port.out.FindBankingPort;
import com.castle.wookpay.banking.application.port.out.external.bank.RequestExternalFirmBankingPort;
import com.castle.wookpay.banking.application.port.out.microservice.membership.ValidateMembershipPort;
import com.castle.wookpay.banking.domain.command.RequestFirmBankingCommand;
import com.castle.wookpay.banking.domain.command.UpdateFirmBankingCommand;
import com.castle.wookpay.banking.domain.entity.FirmBankingRequestJpaEntity;
import com.castle.wookpay.banking.domain.enums.FirmBankingStatus;
import com.castle.wookpay.banking.domain.result.RequestFirmBankingResult;
import com.castle.wookpay.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmBankingService implements RequestFirmBankingUseCase, UpdateFirmBankingUseCase {

	private final RequestFirmBankingPort requestFirmBankingPort;
	private final RequestExternalFirmBankingPort requestExternalFirmBankingPort;
	private final ValidateMembershipPort validateMembershipPort;
	private final FindBankingPort validateBankingPort;
	private final CommandGateway commandGateway;
	@Override
	public RequestFirmBankingResult requestFirmBanking(RequestFirmBankingCommand command) {
		// 유저 검증
		validateMembershipPort.validateMembership(command.membershipId());

		// 유저 계좌 체크
		validateBankingPort.findBankingAccount(command.membershipId());

		// 펌뱅킹 요청 저장
		final FirmBankingRequestJpaEntity firmBankingRequest = requestFirmBankingPort.createFirmBankingRequest(
				command.fromBankName(),
				command.fromBankAccountNumber(),
				command.toBankName(),
				command.toBankAccountNumber(),
				command.moneyAmount(),
				FirmBankingStatus.BEFORE,
				""
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

	@Override
	public void requestFirmBankingByEvent(RequestFirmBankingCommand command) {

		//command -> Event Sourcing
		CreateFrimbankingRequestCommand createFrimbankingRequestCommand = CreateFrimbankingRequestCommand.builder()
				.toBankName(command.toBankName())
				.toBankAccountNumber(command.toBankAccountNumber())
				.fromBankName(command.fromBankName())
				.fromBankAccountNumber(command.fromBankAccountNumber())
				.build();

		commandGateway.send(createFrimbankingRequestCommand).whenComplete(
				(result, throwable) -> {
					if (throwable != null) {
						throwable.printStackTrace();
					} else {
						System.out.println("createFirmbankingRequestCommand completed, Aggregate ID: " + result.toString() );
						// DB save

						// 펌뱅킹 요청 저장
						final FirmBankingRequestJpaEntity firmBankingRequest = requestFirmBankingPort.createFirmBankingRequest(
								command.fromBankName(),
								command.fromBankAccountNumber(),
								command.toBankName(),
								command.toBankAccountNumber(),
								command.moneyAmount(),
								FirmBankingStatus.BEFORE,
								result.toString()
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

					}
				}
		);

	}

	private void changeFirmBankingRequestStatus(FirmBankingRequestJpaEntity firmBankingRequest, ExRequestFirmBankingResponse exRequestFirmBankingResponse) {
		if (exRequestFirmBankingResponse.resultCode() == 0) {
			firmBankingRequest.changeStatus(FirmBankingStatus.SUCCESS);
		} else {
			firmBankingRequest.changeStatus(FirmBankingStatus.FAIL);
		}
	}

	@Override
	public void updateFirmBankingByEvent(UpdateFirmBankingCommand command) {

		// Command
		UpdateFirmBankingRequestCommand updateFirmBankingRequestCommand =
				new UpdateFirmBankingRequestCommand(command.getFromBankAggregateIdentifier(), command.getFirmBankingStatus());

		commandGateway.send(updateFirmBankingRequestCommand)
				.whenComplete((result, throwable) -> {
					if (throwable != null) {
						throwable.printStackTrace();
					} else {
						System.out.println("updateFirmBankingRequestCommand completed");

						// firmBankingRequest 수정

						// status의 변경으로 인한 외부 은행과의 커뮤니케이션
						// 기존 펌뱅킹 정보에서 from <-> to 가 변경된 펌뱅킹을 요청하는 새로운 요청 
					}
				});
	}
}
