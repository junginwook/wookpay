package com.castle.wookpay.money.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.castle.wookpay.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.castle.wookpay.money.adapter.axon.event.MemberMoneyCreatedEvent;
import com.castle.wookpay.money.domain.command.IncreaseMoneyChangingCommand;
import java.util.UUID;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate()
@Data
public class MemberMoneyAggregate {

	@AggregateIdentifier
	private String id;

	private Long membershipId;

	private int balance;

	@CommandHandler
	public MemberMoneyAggregate(MemberMoneyCreatedCommand command) {
		System.out.println("MemberMoneyCreatedCommand Handler");

		apply(new MemberMoneyCreatedEvent(command.membershipId()));
	}

	@CommandHandler
	public String handle(IncreaseMoneyChangingCommand command) {
		System.out.println("IncreaseMoneyChangingCommand Handler");
		id = command.aggregateIdentifier();

		apply(new IncreaseMoneyChangingCommand(command.targetMembershipId(), command.amount(), id));
		return id;
	}

	@EventSourcingHandler
	public void on(MemberMoneyCreatedEvent event) {
		System.out.println("MemberMoneyCreatedEvent Sourcing Handler");
		id = UUID.randomUUID().toString();
		membershipId = Long.parseLong(event.membershipId());
		balance = 0;
	}

	@EventSourcingHandler
	public void on(IncreaseMoneyChangingCommand event) {
		System.out.println("MemberMoneyCreatedEvent Sourcing Handler");
		id = event.aggregateIdentifier();
		membershipId = Long.parseLong(event.targetMembershipId());
		balance = event.amount();
	}

	public MemberMoneyAggregate() {
	}
}
