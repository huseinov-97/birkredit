package com.jkapital.birbank.controller;

import com.jkapital.birbank.model.dto.PaymentHistoryRequest;
import com.jkapital.birbank.model.dto.PaymentHistoryResponse;
import com.jkapital.birbank.service.PaymentHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment-history")
public class PaymentHistoryController {

    private final PaymentHistoryService service;

    @PostMapping
    public List<PaymentHistoryResponse> createAndGetList(@RequestBody PaymentHistoryRequest request) {
        return service.createAndGetList(request);
    }
}
