package com.jkapital.birbank.repo;

import com.jkapital.birbank.model.Loan;
import com.jkapital.birbank.model.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Integer> {
    List<PaymentHistory> findByLoan(Loan loan);
}
