package com.jkapital.birbank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistoryResponse {
    private Integer amount;
    private LocalDate date;
    private String name;
    private String surname;
    private Integer creditId;
}
