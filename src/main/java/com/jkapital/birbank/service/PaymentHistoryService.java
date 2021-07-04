package com.jkapital.birbank.service;

import com.jkapital.birbank.model.dto.PaymentHistoryRequest;
import com.jkapital.birbank.model.dto.PaymentHistoryResponse;

import java.util.List;

public interface PaymentHistoryService {

    List<PaymentHistoryResponse> createAndGetList(PaymentHistoryRequest request);
}
