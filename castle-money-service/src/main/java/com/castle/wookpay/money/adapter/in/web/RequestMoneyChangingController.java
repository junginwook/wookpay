package com.castle.wookpay.money.adapter.in.web;

import com.castle.wookpay.common.annotation.WebAdapter;
import com.castle.wookpay.common.http.ApiResponse;
import com.castle.wookpay.money.adapter.in.web.request.DecreaseMoneyChangingRequest;
import com.castle.wookpay.money.adapter.in.web.request.IncreaseMoneyChangingRequest;
import com.castle.wookpay.money.adapter.in.web.response.DecreaseMoneyChangingResponse;
import com.castle.wookpay.money.adapter.in.web.response.IncreaseMoneyChangingResponse;
import com.castle.wookpay.money.application.port.in.IncreaseMoneyUseCase;
import com.castle.wookpay.money.application.port.in.command.IncreaseMoneyChangingCommand;
import com.castle.wookpay.money.domain.MoneyChangingRequest;
import com.castle.wookpay.money.domain.enums.MoneyChangingResultStatus;
import com.castle.wookpay.money.domain.enums.MoneyChangingType;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/money")
@RequiredArgsConstructor
public class RequestMoneyChangingController {

	private final IncreaseMoneyUseCase increaseMoneyUseCase;

	@Operation(summary = "머니 감액 요청")
	@PostMapping(path = "/v1/money/increase")
	public ApiResponse<IncreaseMoneyChangingResponse> increaseMoney(
			@Valid @RequestBody IncreaseMoneyChangingRequest request) {

		MoneyChangingRequest moneyChangingRequest = increaseMoneyUseCase.increaseMoney(
				new IncreaseMoneyChangingCommand(request.targetMembershipId(), request.amount())
		);

		return new ApiResponse<>(
				new IncreaseMoneyChangingResponse(
						moneyChangingRequest.getMoneyChangingRequestId(),
						MoneyChangingType.INCREASE,
						moneyChangingRequest.getChangingMoneyResultStatus(),
						moneyChangingRequest.getChangingMoneyAmount()),
				HttpStatus.OK
		);
	}

	@Operation(summary = "머니 감액 요청")
	@PostMapping(path = "/v1/money/decrease")
	public ApiResponse<DecreaseMoneyChangingResponse> decreaseMoney(
			@Valid @RequestBody DecreaseMoneyChangingRequest request) {

		MoneyChangingRequest moneyChangingRequest = increaseMoneyUseCase.increaseMoney(
				new IncreaseMoneyChangingCommand(request.targetMembershipId(), request.amount())
		);

		return new ApiResponse<>(
				new DecreaseMoneyChangingResponse("", MoneyChangingType.INCREASE, MoneyChangingResultStatus.LACK_OF_BALANCE, 1000L),
				HttpStatus.OK
		);
	}

}
