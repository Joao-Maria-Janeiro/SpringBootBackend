package com.sap.intern.ecommerce.service;

import com.sap.intern.ecommerce.model.Customer;
import lombok.NonNull;

public interface CustomerService {

    Customer findByEmail(@NonNull final String email);

    Customer save(Customer customer);

    Iterable<Customer> findAll();

    Customer findByToken(String token);
}
