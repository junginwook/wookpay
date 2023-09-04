package com.castle.wookpay.banking.adapter.in.web;

import com.castle.wookpay.banking.application.port.in.ValidateBankingUseCase;
import com.castle.wookpay.banking.domain.command.ValidateBankAccountCommand;
import com.castle.wookpay.banking.domain.response.ValidateBankingResponse;
import com.castle.wookpay.banking.domain.result.ValidateBankingResult;
import com.castle.wookpay.common.annotation.WebAdapter;
import com.castle.wookpay.common.http.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/banking/internal/v1")
@RequiredArgsConstructor
public class ValidateBankingController {
	private final ValidateBankingUseCase validateBankingUseCase;

	@Operation(summary = "멤버 계좌 검증")
	@GetMapping("/account/{memberId}")
	public ApiResponse<ValidateBankingResponse> validateBankAccount(@PathVariable("memberId") String memberId) {
		ValidateBankingResult result = validateBankingUseCase.validateBankingAccount(
				new ValidateBankAccountCommand(memberId)
		);

		return new ApiResponse<>(
				new ValidateBankingResponse(result.bankName(), result.bankAccountNumber()),
				HttpStatus.OK
		);
	}
}
