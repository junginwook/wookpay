package com.castle.wookpay.membership.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "membership")
@Getter
@NoArgsConstructor
public class MembershipJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private String address;

	@Column
	private String email;

	@Column
	private boolean isValid;

	@Column
	private boolean isCorp;

	public MembershipJpaEntity(String name, String address, String email, boolean isValid, boolean isCorp) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.isValid = isValid;
		this.isCorp = isCorp;
	}
}
