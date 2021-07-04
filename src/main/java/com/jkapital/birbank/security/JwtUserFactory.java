package com.jkapital.birbank.security;

import com.jkapital.birbank.model.Employee;
import com.jkapital.birbank.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public static JwtUser create(Employee employee) {
        return JwtUser
                .builder()
                .id(employee.getId())
                .userName(employee.getUserName())
                .password(employee.getPassword())
                .firstname(employee.getName())
                .lastname(employee.getSurname())
                .phone(employee.getPhoneNumber())
                .authorities(mapToGrantedAuthorities(employee.getRoles()))
                .build();
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
