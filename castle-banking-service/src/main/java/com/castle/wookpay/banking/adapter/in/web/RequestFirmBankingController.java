package com.castle.wookpay.banking.adapter.in.web;

import com.castle.wookpay.banking.application.port.in.RequestFirmBankingUseCase;
import com.castle.wookpay.banking.application.port.in.UpdateFirmBankingUseCase;
import com.castle.wookpay.banking.domain.command.RequestFirmBankingCommand;
import com.castle.wookpay.banking.domain.command.UpdateFirmBankingCommand;
import com.castle.wookpay.banking.domain.request.RequestFirmBankingRequest;
import com.castle.wookpay.banking.domain.request.UpdateFirmBankingRequest;
import com.castle.wookpay.banking.domain.response.RequestFirmBankingResponse;
import com.castle.wookpay.banking.domain.result.RequestFirmBankingResult;
import com.castle.wookpay.common.annotation.WebAdapter;
import com.castle.wookpay.common.http.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/banking/internal/v1/")
@RequiredArgsConstructor
public class RequestFirmBankingController {
	private final RequestFirmBankingUseCase requestFirmBankingUseCase;
	private final UpdateFirmBankingUseCase updateFirmBankingUseCase;
	@PostMapping(path = "/firmBanking")
	ApiResponse<RequestFirmBankingResponse> requestFirmBankingResponse(
			@Valid @RequestBody RequestFirmBankingRequest request
	) {
		RequestFirmBankingCommand command = RequestFirmBankingCommand.builder()
				.fromBankName(request.fromBankName())
				.fromBankAccountNumber(request.fromBankAccountNumber())
				.toBankName(request.toBankName())
				.toBankAccountNumber(request.toBankAccountNumber())
				.moneyAmount(request.moneyAmount())
				.membershipId(request.membershipId())
				.build();

		RequestFirmBankingResult result = requestFirmBankingUseCase.requestFirmBanking(command);
		return new ApiResponse<>(
			new RequestFirmBankingResponse(
				result.firmBankingRequestId(),
				result.fromBankName(),
				request.fromBankAccountNumber(),
				request.toBankName(),
				request.toBankAccountNumber(),
				request.moneyAmount()
			),
			HttpStatus.OK
		);
	}

	@PostMapping(path = "/firmBanking-eda")
	void requestFirmBankingResponseByEvent(
			@Valid @RequestBody RequestFirmBankingRequest request
	) {
		RequestFirmBankingCommand command = RequestFirmBankingCommand.builder()
				.fromBankName(request.fromBankName())
				.fromBankAccountNumber(request.fromBankAccountNumber())
				.toBankName(request.toBankName())
				.toBankAccountNumber(request.toBankAccountNumber())
				.moneyAmount(request.moneyAmount())
				.membershipId(request.membershipId())
				.build();

		requestFirmBankingUseCase.requestFirmBankingByEvent(command);
	}

	@PutMapping(path = "/firmBanking/update-eda")
	void updateFirmBankingResponseByEvent(
			@Valid @RequestBody UpdateFirmBankingRequest request
	) {
		UpdateFirmBankingCommand command = UpdateFirmBankingCommand.builder()
				.fromBankAggregateIdentifier(request.firmbankingRequestAggregateIdentifier())
				.firmBankingStatus(request.status())
				.build();

		updateFirmBankingUseCase.updateFirmBankingByEvent(command);
	}
}
