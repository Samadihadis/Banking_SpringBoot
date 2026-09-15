package com.samadihadis.Banking.repository;

import com.samadihadis.Banking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByNationalId(String nationalId);
    Optional<Customer> findByCustomerCode(String customerCode);

}
