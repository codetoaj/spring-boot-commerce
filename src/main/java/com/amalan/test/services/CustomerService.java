package com.amalan.test.services;

import com.amalan.test.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerService extends JpaRepository<Customers, Integer> {
    Boolean existsByPhonenumber(String phonenumber);
}
