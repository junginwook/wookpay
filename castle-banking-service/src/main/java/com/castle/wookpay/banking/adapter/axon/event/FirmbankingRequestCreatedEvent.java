package com.castle.wookpay.banking.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FirmbankingRequestCreatedEvent {

	private String fromBankName;
	private String fromBankAccountNumber;
	private String toBankName;
	private String toBankAccountNumber;
	private int moneyAmount;
}
