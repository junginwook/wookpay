package com.castle.wookpay.banking.domain.entity;

import com.castle.wookpay.banking.domain.enums.FirmBankingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "request_firm_banking")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FirmBankingRequestJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fromBankName;
	private String fromBankAccountNumber;
	private String toBankName;
	private String toBankAccountNumber;
	private Long moneyAmount;
	private FirmBankingStatus firmBankingStatus;

	public FirmBankingRequestJpaEntity(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, Long moneyAmount, FirmBankingStatus firmBankingStatus) {
		this.fromBankName = fromBankName;
		this.fromBankAccountNumber = fromBankAccountNumber;
		this.toBankName = toBankName;
		this.toBankAccountNumber = toBankAccountNumber;
		this.moneyAmount = moneyAmount;
		this.firmBankingStatus = firmBankingStatus;
	}

	public void changeStatus(FirmBankingStatus status) {
		this.firmBankingStatus = status;
	}
}
