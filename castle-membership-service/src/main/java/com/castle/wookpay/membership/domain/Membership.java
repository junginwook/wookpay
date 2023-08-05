package com.castle.wookpay.membership.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {

	@Getter private final String memberShipId;

	@Getter private final String name;

	@Getter private final String email;

	@Getter private final String address;

	@Getter private final boolean isValid;

	@Getter private final boolean isCorp;

	//Membership
	//오염이 되면 안되는 클래스, 고객 정보, 핵심 도메인
	//안전하게 사용될 수 있도록
	public static Membership generateMember(
			MemberShipId memberShipId,
			MemberShipName memberShipName,
			MemberShipEmail memberShipEmail,
			MemberShipAddress memberShipAddress,
			MemberShipIsValid memberShipIsValid,
			MemberShipIsCorp memberShipIsCorp
	) {
		return new Membership(
				memberShipId.memberShipId,
				memberShipName.memberShipName,
				memberShipEmail.memberShipEmail,
				memberShipAddress.memberShipAddress,
				memberShipIsValid.memberShipIsValid,
				memberShipIsCorp.memberShipIsCorp);
	}

	@Value
	public static class MemberShipId {
		String memberShipId;

		public MemberShipId(String value) {
			this.memberShipId = value;
		}
	}

	@Value
	public static class MemberShipName {
		String memberShipName;

		public MemberShipName(String value) {
			this.memberShipName = value;
		}
	}

	@Value
	public static class MemberShipEmail {
		String memberShipEmail;

		public MemberShipEmail(String value) {
			this.memberShipEmail = value;
		}
	}

	@Value
	public static class MemberShipAddress {
		String memberShipAddress;

		public MemberShipAddress(String value) {
			this.memberShipAddress = value;
		}
	}

	@Value
	public static class MemberShipIsValid {
		boolean memberShipIsValid;

		public MemberShipIsValid(boolean value) {
			this.memberShipIsValid = value;
		}
	}

	@Value
	public static class MemberShipIsCorp {
		boolean memberShipIsCorp;

		public MemberShipIsCorp(boolean value) {
			this.memberShipIsCorp = value;
		}
	}
}
