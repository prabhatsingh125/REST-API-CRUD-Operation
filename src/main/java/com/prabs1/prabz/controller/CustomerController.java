package com.prabs1.prabz.controller;

import com.prabs1.prabz.model.Customer;
import com.prabs1.prabz.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // GetAll
    @GetMapping("/customers")
    List<Customer> getAll(){
        return customerService.listAll();
    }

    // getById
    @GetMapping("/customers/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        try {
            Customer customer=customerService.get(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>("Customer does not exist with this Id",HttpStatus.NOT_FOUND);
        }


    }

    // Create
    @PostMapping("/customers")
    public ResponseEntity<?> add(@RequestBody Customer customer){
        customerService.save(customer);
        return new ResponseEntity<>("Value Added",HttpStatus.OK);
    }

    // Update (This is not working properly)
    @PutMapping("/customers/{id}")
    public ResponseEntity<String> update(@RequestBody Customer customer, @PathVariable Long id){
        try {
            Customer prevCustomer = customerService.get(id);
            if(prevCustomer==null) {
                customerService.save(customer);
                return new ResponseEntity<>("Updated New value", HttpStatus.OK);
            }


        } catch (NoSuchElementException e){
            Customer prevCustomer=customerService.get(id);
            prevCustomer.setName(customer.getName());
            prevCustomer.setMobileNumber(customer.getMobileNumber());
            prevCustomer.setEmailid(customer.getEmailid());
            prevCustomer.setAddress(customer.getAddress());
            return new ResponseEntity<>("Updated on previous value",HttpStatus.OK);
        }
        return null;
    }

    // Delete_By_Id
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            Customer customer=customerService.get(id);
            customerService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>("Customer does not exist with this Id",HttpStatus.NOT_FOUND);
        }
    }




}
