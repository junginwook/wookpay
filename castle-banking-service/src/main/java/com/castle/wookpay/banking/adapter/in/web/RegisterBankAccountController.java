package com.castle.wookpay.banking.adapter.in.web;

import com.castle.wookpay.banking.application.port.in.RegisterBankAccountUseCase;
import com.castle.wookpay.banking.domain.command.RegisterBankAccountCommand;
import com.castle.wookpay.banking.domain.request.RegisterBankAccountRequest;
import com.castle.wookpay.banking.domain.response.RegisterBankAccountResponse;
import com.castle.wookpay.banking.domain.result.RegisterBankAccountResult;
import com.castle.wookpay.common.annotation.WebAdapter;
import com.castle.wookpay.common.http.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/banking/v1")
@RequiredArgsConstructor
public class RegisterBankAccountController {
	private final RegisterBankAccountUseCase registerBankAccountUseCase;

	@PostMapping(path = "/account")
	ApiResponse<RegisterBankAccountResponse> registerBankAccount(
			@Valid @RequestBody RegisterBankAccountRequest request
	) {
		RegisterBankAccountCommand command = new RegisterBankAccountCommand(request.membershipId(), request.bankName(), request.bankAccountNumber());
		RegisterBankAccountResult result = registerBankAccountUseCase.registerBankAccount(command);

		return new ApiResponse<>(
			 new RegisterBankAccountResponse(
					result.registeredBankAccountId(),
					result.membershipId(),
					result.bankName(),
					result.bankAccountNumber()
			), HttpStatus.OK);
	}

	@PostMapping(path = "/account/register-eda")
	void registerBankAccountByEvent(
			@Valid @RequestBody RegisterBankAccountRequest request
	) {
		RegisterBankAccountCommand command = new RegisterBankAccountCommand(request.membershipId(), request.bankName(), request.bankAccountNumber());
		RegisterBankAccountResult result = registerBankAccountUseCase.registerBankAccount(command);
	}
}
