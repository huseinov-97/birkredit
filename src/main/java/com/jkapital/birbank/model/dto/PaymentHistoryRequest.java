package com.jkapital.birbank.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jkapital.birbank.model.PaymentHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistoryRequest {
    private Integer creditId;
    private Integer amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    public PaymentHistory toPaymentHistory() {
        return PaymentHistory
                .builder()
                .id(creditId)
                .amount(amount)
                .date(date)
                .build();
    }
}
