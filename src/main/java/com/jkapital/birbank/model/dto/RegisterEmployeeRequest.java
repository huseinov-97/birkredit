package com.jkapital.birbank.model.dto;

import com.jkapital.birbank.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEmployeeRequest {
    private String userName;
    private String password;
    private String firstname;
    private String surname;
    private String phoneNumber;

    public Employee toEmployee() {
        return Employee
                .builder()
                .userName(userName)
                .password(password)
                .name(firstname)
                .surname(surname)
                .phoneNumber(phoneNumber)
                .build();
    }
}
