package com.castle.wookpay.banking.adapter.out.external.bank.request;

public record RequestFirmBankingRequest(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber) {
}
