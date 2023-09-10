package com.castle.wookpay.banking.domain.command;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode
public class UpdateFirmBankingCommand {

	private final String fromBankAggregateIdentifier;
	private final int firmBankingStatus;
}
