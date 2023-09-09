package com.castle.wookpay.money.application.service;

import com.castle.wookpay.common.annotation.UseCase;
import com.castle.wookpay.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.castle.wookpay.money.application.port.in.CreateMemberMoneyUseCase;
import com.castle.wookpay.money.application.port.out.persistence.CreateMemberMoneyPort;
import com.castle.wookpay.money.domain.command.CreateMemberMoneyCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class CreateMemberMoneyService implements CreateMemberMoneyUseCase {

	private final CommandGateway commandGateway;
	private final CreateMemberMoneyPort createMemberMoneyPort;

	@Override
	public void createMemberMoney(CreateMemberMoneyCommand command) {
		MemberMoneyCreatedCommand axonCommand = new MemberMoneyCreatedCommand(command.membershipId());
		// event sourcing
		commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
			if (throwable != null) {
				System.out.println("error");
				throw new RuntimeException(throwable);
			} else {
				System.out.println("result=" + result);
				createMemberMoneyPort.createMemberMoney(
						command.membershipId(),
						result.toString()
				);
			}
		});
	}
}
