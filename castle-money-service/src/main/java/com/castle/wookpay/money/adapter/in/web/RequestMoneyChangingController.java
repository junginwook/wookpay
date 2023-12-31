package com.castle.wookpay.money.adapter.in.web;

import com.castle.wookpay.common.annotation.WebAdapter;
import com.castle.wookpay.common.http.ApiResponse;
import com.castle.wookpay.money.application.port.in.CreateMemberMoneyUseCase;
import com.castle.wookpay.money.domain.command.CreateMemberMoneyCommand;
import com.castle.wookpay.money.domain.request.CreateMemberMoneyRequest;
import com.castle.wookpay.money.domain.request.DecreaseMoneyChangingRequest;
import com.castle.wookpay.money.domain.request.IncreaseMoneyChangingRequest;
import com.castle.wookpay.money.domain.response.DecreaseMoneyChangingResponse;
import com.castle.wookpay.money.domain.response.IncreaseMoneyChangingResponse;
import com.castle.wookpay.money.application.port.in.IncreaseMoneyUseCase;
import com.castle.wookpay.money.domain.command.IncreaseMoneyChangingCommand;
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
	private final CreateMemberMoneyUseCase createMemberMoneyUseCase;
	@Operation(summary = "머니 감액 요청")
	@PostMapping(path = "/money/v1/increase")
	public ApiResponse<IncreaseMoneyChangingResponse> increaseMoney(
			@Valid @RequestBody IncreaseMoneyChangingRequest request) {

		MoneyChangingRequest moneyChangingRequest = increaseMoneyUseCase.increaseMoney(
				new IncreaseMoneyChangingCommand(request.targetMembershipId(), request.amount(), null)
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
	@PostMapping(path = "/money/v1/decrease")
	public ApiResponse<DecreaseMoneyChangingResponse> decreaseMoney(
			@Valid @RequestBody DecreaseMoneyChangingRequest request) {

		return new ApiResponse<>(
				new DecreaseMoneyChangingResponse("", MoneyChangingType.INCREASE, MoneyChangingResultStatus.LACK_OF_BALANCE, 1000L),
				HttpStatus.OK
		);
	}

	@PostMapping(path = "/money/create-member-money")
	void createMemberMoney(@RequestBody CreateMemberMoneyRequest request) {
		createMemberMoneyUseCase.createMemberMoney(new CreateMemberMoneyCommand(request.membershipId()));

	}

	@PostMapping(path = "/money/increase-eda")
	void increaseMoneyChangingRequestByEvent(@RequestBody IncreaseMoneyChangingRequest request) {
		IncreaseMoneyChangingCommand command = new IncreaseMoneyChangingCommand(request.targetMembershipId(), request.amount(), request.aggregateIdentifier());

		increaseMoneyUseCase.increaseMoneyRequestByEvent(command);
	}
}
