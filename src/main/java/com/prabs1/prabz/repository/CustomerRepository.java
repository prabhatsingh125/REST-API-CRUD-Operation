package com.prabs1.prabz.repository;

import com.prabs1.prabz.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
