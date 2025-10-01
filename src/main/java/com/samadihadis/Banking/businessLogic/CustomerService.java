package com.samadihadis.Banking.businessLogic;


import com.samadihadis.Banking.entity.Customer;
import com.samadihadis.Banking.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
