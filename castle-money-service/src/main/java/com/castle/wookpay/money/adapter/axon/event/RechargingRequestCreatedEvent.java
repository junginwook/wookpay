package com.castle.wookpay.money.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargingRequestCreatedEvent {
	// 충전 동작을 요청이 생성되었다는 "이벤트"
	private String rechargingRequestId; // 사가에서 유일하게 생성되는 값
	private String membershipId;
	private int amount;
}
