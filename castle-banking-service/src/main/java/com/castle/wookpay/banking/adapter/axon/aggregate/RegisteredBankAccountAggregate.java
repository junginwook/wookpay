package com.castle.wookpay.banking.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.castle.wookpay.banking.adapter.axon.command.CreateRegisteredBankAccountCommand;
import com.castle.wookpay.banking.adapter.axon.event.CreateRegisteredBankAccountEvent;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate()
public class RegisteredBankAccountAggregate {

	@AggregateIdentifier
	private String id;

	private String membershipId;

	private String bankName;

	private String bankAccountNumber;

	@CommandHandler
	public RegisteredBankAccountAggregate(@NotNull CreateRegisteredBankAccountCommand command) {

		apply(new CreateRegisteredBankAccountEvent(command.getMembershipId(), command.getBankName(), command.getBankAccountNumber()));
	}

	@EventSourcingHandler
	public void on (CreateRegisteredBankAccountEvent event) {
		System.out.println("CreateRegisteredBankAccountEvent event");
		id = UUID.randomUUID().toString();
		membershipId = event.getMembershipId();
		bankName = event.getBankName();
		bankAccountNumber = event.getBankAccountNumber();
	}
}
