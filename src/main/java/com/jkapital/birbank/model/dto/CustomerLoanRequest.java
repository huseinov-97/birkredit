package com.jkapital.birbank.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jkapital.birbank.model.Customer;
import com.jkapital.birbank.model.Loan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLoanRequest {
    private String name;
    private String surname;
    private String address;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String phoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Integer duration;
    private Integer amount;
    private Integer percentage;

    public Customer toCustomer() {
        return Customer
                .builder()
                .name(name)
                .surname(surname)
                .address(address)
                .dateOfBirth(birthDate)
                .phoneNumber(phoneNumber)
                .build();
    }
    public Loan toLoan(){
        return Loan
                .builder()
                .startDate(startDate)
                .endDate(endDate)
                .duration(duration)
                .amount(amount)
                .percentage(percentage)
                .build();
    }
}
