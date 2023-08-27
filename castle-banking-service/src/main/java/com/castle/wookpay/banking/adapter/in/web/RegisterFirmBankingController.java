package com.castle.wookpay.banking.adapter.in.web;

import com.castle.wookpay.common.annotation.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/banking/v1")
@RequiredArgsConstructor
public class RegisterFirmBankingController {

}
