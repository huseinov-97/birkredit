package com.jkapital.birbank.controller;


import com.jkapital.birbank.model.dto.CustomerLoanRequest;
import com.jkapital.birbank.model.dto.PaymentScheduleResponse;
import com.jkapital.birbank.service.PaymentScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentScheduleController {

    private final PaymentScheduleService service;

    @PostMapping
    public List<PaymentScheduleResponse> createAndGetList(@RequestBody @Validated CustomerLoanRequest request) {
        return service.createAndGetList(request);
    }
}
