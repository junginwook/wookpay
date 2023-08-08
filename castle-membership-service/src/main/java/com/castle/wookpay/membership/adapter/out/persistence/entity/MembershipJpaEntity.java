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
	private String password;

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

	private MembershipJpaEntity(String name, String email, String password, String address, boolean isValid, boolean isCorp) {
		this.name = name;
		this.address = address;
		this.password = password;
		this.email = email;
		this.isValid = isValid;
		this.isCorp = isCorp;
	}

	public static MembershipJpaEntity of(String name, String email, String password, String address, boolean isValid, boolean isCorp) {
		return new MembershipJpaEntity(name, email, password, address, isValid, isCorp);
	}
}
