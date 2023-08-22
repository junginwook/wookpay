package com.castle.wookpay.banking.domain.feign.banking;

import com.castle.wookpay.banking.domain.feign.membership.MembershipFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
		name = "banking-client",
		url = "${feign.url.membership}",
		configuration = MembershipFeignConfig.class
)
public interface BankingFeignClient {

}
