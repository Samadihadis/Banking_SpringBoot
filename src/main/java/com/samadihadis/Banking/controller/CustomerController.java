package com.samadihadis.Banking.controller;


import com.samadihadis.Banking.businessLogic.CustomerService;
import com.samadihadis.Banking.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        try {
                Customer createdCustomer = customerService.createCustomer(customer);
                return ResponseEntity.ok(createdCustomer);
            } catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        Customer customer = customerService.getCustomerById(id);
        if (customer != null){
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        try {
            Customer existingCustomer = customerService.getCustomerById(id);
            if (existingCustomer != null) {
                existingCustomer.setCustomerFullName(customerDetails.getCustomerFullName());
                existingCustomer.setNationalId(customerDetails.getNationalId());
                existingCustomer.setCustomerCode(customerDetails.getCustomerCode());

                Customer updatedCustomer = customerService.createCustomer(existingCustomer);
                return ResponseEntity.ok(updatedCustomer);
            }
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

