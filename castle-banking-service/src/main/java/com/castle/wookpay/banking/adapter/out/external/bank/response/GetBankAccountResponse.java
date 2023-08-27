package com.castle.wookpay.banking.adapter.out.external.bank.response;

public record GetBankAccountResponse(String bankName, String bankAccountNumber, boolean isValid) {
}
