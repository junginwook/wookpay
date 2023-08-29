package com.castle.wookpay.banking.adapter.out.external.bank.response;

public record ExGetBankAccountResponse(String bankName, String bankAccountNumber, boolean isValid) {
}
