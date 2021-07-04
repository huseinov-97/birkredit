package com.jkapital.birbank.controller;

import com.jkapital.birbank.model.Employee;
import com.jkapital.birbank.model.dto.LoginRequest;
import com.jkapital.birbank.model.dto.RegisterEmployeeRequest;
import com.jkapital.birbank.security.JwtTokenProvider;
import com.jkapital.birbank.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmployeeService employeeService;

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/register")
    public long register(@RequestBody @Validated RegisterEmployeeRequest request) {
        return employeeService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated LoginRequest requestDto) {

        try {
            String username = requestDto.getUserName();
            String password = requestDto.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            Employee employee = employeeService.findByUserName(username);

            if (employee == null) {
                throw new UsernameNotFoundException("Employee with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, employee.getRoles());
            System.out.println(token);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
