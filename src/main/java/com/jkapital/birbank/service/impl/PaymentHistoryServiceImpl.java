package com.jkapital.birbank.service.impl;

import com.jkapital.birbank.model.Loan;
import com.jkapital.birbank.model.PaymentHistory;
import com.jkapital.birbank.model.dto.PaymentHistoryRequest;
import com.jkapital.birbank.model.dto.PaymentHistoryResponse;
import com.jkapital.birbank.repo.LoanRepository;
import com.jkapital.birbank.repo.PaymentHistoryRepository;
import com.jkapital.birbank.service.PaymentHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    private final LoanRepository loanRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;

    @Override
    public List<PaymentHistoryResponse> createAndGetList(PaymentHistoryRequest request) {
        //find loan for credit id
        Loan loan = loanRepository.findById(request.getCreditId()).get();
        // create new payment history here
        PaymentHistory paymentHistory = request.toPaymentHistory();
        paymentHistory.setLoan(loan);
        paymentHistoryRepository.save(paymentHistory);

        List<PaymentHistory> paymentHistories = paymentHistoryRepository.findByLoan(loan);
        List<PaymentHistoryResponse> paymentHistoryResponses = new ArrayList<>();
        PaymentHistoryResponse paymentHistoryResponse;
        for (PaymentHistory history : paymentHistories) {
            paymentHistoryResponse = new PaymentHistoryResponse();
            paymentHistoryResponse.setAmount(history.getAmount());
            paymentHistoryResponse.setCreditId(history.getLoan().getId());
            paymentHistoryResponse.setName(history.getLoan().getCustomer().getName());
            paymentHistoryResponse.setSurname(history.getLoan().getCustomer().getSurname());
            paymentHistoryResponse.setDate(history.getDate());
            paymentHistoryResponses.add(paymentHistoryResponse);
        }
        return paymentHistoryResponses;
    }
}
