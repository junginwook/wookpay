package com.castle.wookpay.money.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.castle.wookpay.money.adapter.axon.command.IncreaseMoneyCommand;
import com.castle.wookpay.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.castle.wookpay.money.adapter.axon.event.IncreaseMemberMoneyEvent;
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
	public String handle(IncreaseMoneyCommand command) {
		System.out.println("IncreaseMoneyCommand Handler");
		id = command.aggregateIdentifier();

		apply(new IncreaseMemberMoneyEvent(command.targetMembershipId(), command.balance(), id));
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
	public void on(IncreaseMemberMoneyEvent event) {
		System.out.println("IncreaseMemberMoneyEvent Sourcing Handler");
		id = event.aggregateIdentifier();
		membershipId = Long.parseLong(event.membershipId());
		balance = event.amount();
	}

	public MemberMoneyAggregate() {
	}
}
