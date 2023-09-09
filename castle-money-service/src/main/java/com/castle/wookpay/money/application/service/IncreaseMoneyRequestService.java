package com.castle.wookpay.money.application.service;

import com.castle.wookpay.common.annotation.UseCase;
import com.castle.wookpay.money.adapter.out.microservice.banking.request.RequestFirmBankingRequest;
import com.castle.wookpay.money.adapter.out.microservice.banking.response.RequestFirmBankingResponse;
import com.castle.wookpay.money.adapter.out.microservice.banking.response.ValidateBankingResponse;
import com.castle.wookpay.money.adapter.out.microservice.membership.response.ValidateMembershipResponse;
import com.castle.wookpay.money.application.port.in.IncreaseMoneyUseCase;
import com.castle.wookpay.money.application.port.out.persistence.GetMemberMoneyPort;
import com.castle.wookpay.money.application.port.out.service.banking.RequestFirmBankingPort;
import com.castle.wookpay.money.application.port.out.service.banking.ValidateBankingPort;
import com.castle.wookpay.money.application.port.out.service.membership.ValidateMembershipPort;
import com.castle.wookpay.money.domain.command.IncreaseMoneyChangingCommand;
import com.castle.wookpay.money.domain.MoneyChangingRequest;
import com.castle.wookpay.money.domain.entity.MemberMoneyJpaEntity;
import com.castle.wookpay.money.domain.request.IncreaseMoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class IncreaseMoneyRequestService implements IncreaseMoneyUseCase {
	private final ValidateMembershipPort validateMembershipPort;
	private final ValidateBankingPort validateBankingPort;
	private final RequestFirmBankingPort requestFirmBankingPort;
	private final CommandGateway commandGateway;

	private final GetMemberMoneyPort getMemberMoneyPort;

	@Override
	public MoneyChangingRequest increaseMoney(IncreaseMoneyChangingCommand command) {
		//유효한 유저인지 체크
		validateMembershipPort.validateMembership(command.targetMembershipId());

		//유효한 뱅킹 계정인지 확인
		ValidateBankingResponse validateBankingResponse =
				validateBankingPort.validateBanking(command.targetMembershipId());

		//펌뱅킹 요청
		RequestFirmBankingResponse requestFirmBankingResponse = requestFirmBankingPort.requestFirmBanking(
				RequestFirmBankingRequest.builder()
						.membershipId(command.targetMembershipId())
						.fromBankName(validateBankingResponse.bankName())
						.fromBankAccountNumber(validateBankingResponse.bankAccountNumber())
						.toBankName(validateBankingResponse.bankName())
						.toBankAccountNumber(validateBankingResponse.bankAccountNumber())
						.build()
		);

		//머니 서비스 db에 저장


		return null;
	}

	@Override
	public void increaseMoneyRequestByEvent(IncreaseMoneyChangingCommand command) {
		MemberMoneyJpaEntity memberMoneyJpaEntity = getMemberMoneyPort.getMemberMoney(
				command.targetMembershipId()
		);

		String aggregateIdentifier = memberMoneyJpaEntity.getAggregateIdentifier();

		// command
		commandGateway.send(new IncreaseMoneyChangingCommand(
				command.targetMembershipId(),
				command.amount(),
				aggregateIdentifier
		)).whenComplete((result, throwable) -> {
			if (throwable != null) {
				System.out.println("error");
				throw new RuntimeException(throwable);
			} else {
				System.out.println("result=" + result);

				//increase money
			}
		});
	}
}
