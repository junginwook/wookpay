package com.castle.wookpay.banking.adapter.axon.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UpdateFirmBankingRequestCommand {

	@NotNull
	@TargetAggregateIdentifier
	private String aggregateIdentifier;

	private int firmBankingStatus;
}
