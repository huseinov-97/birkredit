package com.jkapital.birbank.service.impl;

import com.jkapital.birbank.model.Customer;
import com.jkapital.birbank.model.Loan;
import com.jkapital.birbank.model.dto.CustomerLoanRequest;
import com.jkapital.birbank.model.dto.PaymentScheduleResponse;
import com.jkapital.birbank.repo.CustomerRepository;
import com.jkapital.birbank.repo.LoanRepository;
import com.jkapital.birbank.service.PaymentScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentScheduleServiceImpl implements PaymentScheduleService {

    private final CustomerRepository customerRepository;
    private final LoanRepository loanRepository;

    @Override
    public List<PaymentScheduleResponse> createAndGetList(CustomerLoanRequest request) {
        //creating customer from request
        Customer customer = request.toCustomer();
        customerRepository.save(customer);

        // creating loan from request
        Loan loan = request.toLoan();
        loan.setCustomer(customer);
        loanRepository.save(loan);

        List<PaymentScheduleResponse> paymentScheduleResponses = new ArrayList<>();

        // find months between start date and end date
        LocalDate endDate = request.getEndDate();
        LocalDate startDate = request.getStartDate();
        int months = (int) startDate.until(endDate, ChronoUnit.MONTHS);

        PaymentScheduleResponse paymentScheduleResponse;

        // calculating amount with charge, credit amount and monthly charge
        int amount = request.getAmount();
        amount += amount * request.getPercentage() / 100;
        int creditAmount = amount / months;
        int monthlyCharge = creditAmount * request.getPercentage()/100;

        //setting values to the payment schedule response
        for (int i=1; i<=months; i++) {
            paymentScheduleResponse = new PaymentScheduleResponse();
            paymentScheduleResponse.setPaymentNo(i);
            paymentScheduleResponse.setPaymentAmount(amount / months);
            paymentScheduleResponse.setMainDebt(loan.getAmount());
            paymentScheduleResponse.setFullCharges(amount - loan.getAmount());
            paymentScheduleResponse.setMonthlyCharges(monthlyCharge);
            paymentScheduleResponse.setRemainingDebt(amount - (creditAmount * (i - 1)));
            paymentScheduleResponse.setPaymentDate(loan.getStartDate().plus(i - 1, ChronoUnit.MONTHS));
            paymentScheduleResponses.add(paymentScheduleResponse);
        }
        return paymentScheduleResponses;
    }
}
