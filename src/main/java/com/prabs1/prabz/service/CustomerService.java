package com.prabs1.prabz.service;

import com.prabs1.prabz.model.Customer;
import com.prabs1.prabz.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    // GetAll
    public List<Customer> listAll(){
        return customerRepository.findAll();
    }

    // Save
    public void save(Customer customer){
        customerRepository.save(customer);
    }

    // GetEach
    public Customer get(Long id){
        return customerRepository.findById(id).get();
    }

    // Delete
    public void delete(Long id){
        customerRepository.deleteById(id);
    }


}
