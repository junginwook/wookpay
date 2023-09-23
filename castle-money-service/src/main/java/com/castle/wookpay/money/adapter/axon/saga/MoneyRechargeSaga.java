package com.castle.wookpay.money.adapter.axon.saga;

import com.castle.wookpay.common.event.CheckRegisteredBankAccountCommand;
import com.castle.wookpay.money.adapter.axon.event.RechargingRequestCreatedEvent;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@NoArgsConstructor
public class MoneyRechargeSaga {

	@NotNull
	private transient CommandGateway commandGateway;

	@Autowired
	private void setCommandGateway(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@StartSaga // 하나의 어플리케이션에는 여러개의 사가가 존재할 수 있다. 구분하기 위한 구분자
	@SagaEventHandler(associationProperty = "rechargingRequestId")
	public void handle(RechargingRequestCreatedEvent event) {
		System.out.println("RechargingRequestCreatedEvent Start saga");
		String rechargingRequestId = event.getRechargingRequestId();

		// 일련의 핸들 작업을 하는데, 앞으로의 핸들 동작에서 어떤 것을 associationKey로 활용할 것인지 지정
		SagaLifecycle.associateWith("rechargingRequestId", rechargingRequestId);

		// "충전 요청" 이 시작 되었다.

		// 뱅킹의 계좌 등록 여부 확인하기
		// CheckRegisteredBankAccountCommand -> Check Bank Account
		// -> axon server -> Banking Service -> Command
		commandGateway.send(new CheckRegisteredBankAccountCommand(
				"",
				rechargingRequestId,
				event.getMembershipId()));
	}
}
