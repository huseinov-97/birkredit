package com.jkapital.birbank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentScheduleResponse {
    private Integer paymentNo;
    private LocalDate paymentDate;
    private Integer paymentAmount;
    private Integer mainDebt;
    private Integer fullCharges;
    private Integer monthlyCharges;
    private Integer remainingDebt;
}
