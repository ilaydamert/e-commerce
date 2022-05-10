package com.ecommerce.ecommerce.Repositories;

import com.ecommerce.ecommerce.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAll();

    Customer findByUsername(String username);
    Customer findCustomerById(int id);

}

