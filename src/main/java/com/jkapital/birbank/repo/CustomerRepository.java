package com.jkapital.birbank.repo;

import com.jkapital.birbank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByNameAndPhoneNumber(String name,String phoneNumber);
}
