package com.jkapital.birbank.service;

import com.jkapital.birbank.model.dto.CustomerLoanRequest;
import com.jkapital.birbank.model.dto.PaymentScheduleResponse;

import java.util.List;

public interface PaymentScheduleService {
    List<PaymentScheduleResponse> createAndGetList(CustomerLoanRequest request);
}
