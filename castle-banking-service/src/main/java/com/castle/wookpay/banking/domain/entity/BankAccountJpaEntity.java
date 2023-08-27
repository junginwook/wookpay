package com.castle.wookpay.banking.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_account")
@Getter
@NoArgsConstructor
public class BankAccountJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long registeredBankAccountId;

	@Column
	private String memberShipId;

	@Column
	private String bankName;

	@Column
	private String bankAccountNumber;

	public BankAccountJpaEntity(String memberShipId, String bankName, String bankAccountNumber) {
		this.memberShipId = memberShipId;
		this.bankName = bankName;
		this.bankAccountNumber = bankAccountNumber;
	}
}
