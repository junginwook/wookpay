package com.castle.wookpay.money.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "member_money")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberMoneyJpaEntity {
	@Id
	@GeneratedValue
	private Long memberMoneyId;

	private Long membershipId;

	private int balance;

	private String aggregateIdentifier;

	public MemberMoneyJpaEntity(Long membershipId, int balance, String aggregateIdentifier) {
		this.membershipId = membershipId;
		this.balance = balance;
		this.aggregateIdentifier = aggregateIdentifier;
	}
}
