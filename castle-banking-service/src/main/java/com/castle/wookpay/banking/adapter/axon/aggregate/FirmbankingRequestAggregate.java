package com.castle.wookpay.banking.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.castle.wookpay.banking.adapter.axon.command.CreateFrimbankingRequestCommand;
import com.castle.wookpay.banking.adapter.axon.event.FirmBankingRequestUpdatedEvent;
import com.castle.wookpay.banking.adapter.axon.event.FirmbankingRequestCreatedEvent;
import com.castle.wookpay.banking.domain.command.UpdateFirmBankingCommand;
import java.util.UUID;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate()
@Data
public class FirmbankingRequestAggregate {

	@AggregateIdentifier
	private String id;

	private String fromBankName;
	private String fromBankAccountNumber;
	private String toBankName;
	private String toBankAccountNumber;
	private int moneyAmount;
	private int firmbankingStatus;

	@CommandHandler
	public FirmbankingRequestAggregate(CreateFrimbankingRequestCommand command) {

		System.out.println("CreateFrimbankingRequestCommand Handler");

		apply(new FirmbankingRequestCreatedEvent(command.getFromBankName(), command.getFromBankAccountNumber(), command.getToBankName(), command.getToBankAccountNumber(), command.getMoneyAmount()));
	}

	@CommandHandler
	public String handle(UpdateFirmBankingCommand command) {

		System.out.println("UpdateFirmBankingCommand Handler");

		id = command.getFromBankAggregateIdentifier();
		apply(new FirmBankingRequestUpdatedEvent(command.getFirmBankingStatus()));

		return id;
	}

	@EventSourcingHandler
	public void on(FirmbankingRequestCreatedEvent event) {
		id = UUID.randomUUID().toString();
		fromBankName = event.getFromBankName();
		fromBankAccountNumber = event.getFromBankAccountNumber();
		toBankName = event.getToBankName();
		toBankAccountNumber = event.getToBankAccountNumber();
	}

	@EventSourcingHandler
	public void on(FirmBankingRequestUpdatedEvent event) {
		firmbankingStatus = event.getFirmBankingStatus();
	}

	public FirmbankingRequestAggregate() {

	}
}
