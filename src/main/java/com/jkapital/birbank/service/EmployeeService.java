package com.jkapital.birbank.service;

import com.jkapital.birbank.model.Employee;
import com.jkapital.birbank.model.dto.RegisterEmployeeRequest;

public interface EmployeeService {
    long register(RegisterEmployeeRequest request);

    Employee findByUserName(String username);
}
