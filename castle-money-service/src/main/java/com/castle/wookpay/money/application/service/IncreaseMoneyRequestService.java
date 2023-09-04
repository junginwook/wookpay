package com.castle.wookpay.money.application.service;

import com.castle.wookpay.common.annotation.UseCase;
import com.castle.wookpay.money.adapter.out.microservice.banking.request.RequestFirmBankingRequest;
import com.castle.wookpay.money.adapter.out.microservice.banking.response.RequestFirmBankingResponse;
import com.castle.wookpay.money.adapter.out.microservice.banking.response.ValidateBankingResponse;
import com.castle.wookpay.money.adapter.out.microservice.membership.response.ValidateMembershipResponse;
import com.castle.wookpay.money.application.port.in.IncreaseMoneyUseCase;
import com.castle.wookpay.money.application.port.out.service.banking.RequestFirmBankingPort;
import com.castle.wookpay.money.application.port.out.service.banking.ValidateBankingPort;
import com.castle.wookpay.money.application.port.out.service.membership.ValidateMembershipPort;
import com.castle.wookpay.money.domain.command.IncreaseMoneyChangingCommand;
import com.castle.wookpay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class IncreaseMoneyRequestService implements IncreaseMoneyUseCase {
	private final ValidateMembershipPort validateMembershipPort;
	private final ValidateBankingPort validateBankingPort;
	private final RequestFirmBankingPort requestFirmBankingPort;
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
}
