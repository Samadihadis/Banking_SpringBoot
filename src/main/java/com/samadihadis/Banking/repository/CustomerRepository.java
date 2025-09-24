package com.samadihadis.Banking.repository;

import com.samadihadis.Banking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
