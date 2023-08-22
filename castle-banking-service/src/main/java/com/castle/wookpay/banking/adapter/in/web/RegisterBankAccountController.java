package com.castle.wookpay.banking.adapter.in.web;

import com.castle.wookpay.banking.application.port.in.RegisterBankAccountUseCase;
import com.castle.wookpay.banking.domain.command.RegisterBankAccountCommand;
import com.castle.wookpay.banking.domain.request.RegisterBankAccountRequest;
import com.castle.wookpay.banking.domain.response.RegisterBankAccountResponse;
import com.castle.wookpay.banking.domain.result.RegisterBankAccountResult;
import com.castle.wookpay.common.annotation.WebAdapter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter
@RequiredArgsConstructor
public class RegisterBankAccountController {
	private final RegisterBankAccountUseCase registerBankAccountUseCase;

	@PostMapping(path = "/banking/v1/account")
	RegisterBankAccountResponse registerBankAccount(
			@Valid @RequestBody RegisterBankAccountRequest request
	) {
		RegisterBankAccountCommand command = new RegisterBankAccountCommand(request.membershipId(), request.bankName(), request.bankAccountNumber());
		RegisterBankAccountResult result = registerBankAccountUseCase.registerBankAccount(command);

		return new RegisterBankAccountResponse(
				result.registeredBankAccountId(),
				request.membershipId(),
				request.bankName(),
				result.bankAccountNumber(),
				result.linkedStatusIsValid()
		);
	}
}
