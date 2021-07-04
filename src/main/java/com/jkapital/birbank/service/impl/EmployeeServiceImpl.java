package com.jkapital.birbank.service.impl;

import com.jkapital.birbank.model.Employee;
import com.jkapital.birbank.model.Role;
import com.jkapital.birbank.model.dto.RegisterEmployeeRequest;
import com.jkapital.birbank.repo.EmployeeRepository;
import com.jkapital.birbank.repo.RoleRepository;
import com.jkapital.birbank.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";

    @Override
    public long register(RegisterEmployeeRequest request) {
        Role roleEmployee = roleRepository.findByName(ROLE_EMPLOYEE);
        List<Role> employeeRoles = new ArrayList<>();
        employeeRoles.add(roleEmployee);

        Employee employee = request.toEmployee();
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRoles(employeeRoles);
        employeeRepository.save(employee);
        return employee.getId();
    }

    public Employee findByUserName(String username) {
        Employee result = employeeRepository.findByUserName(username);
        return result;
    }
}
