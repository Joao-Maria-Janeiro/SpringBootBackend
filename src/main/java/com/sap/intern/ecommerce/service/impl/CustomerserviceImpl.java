package com.sap.intern.ecommerce.service.impl;

import com.sap.intern.ecommerce.exception.NotFoundException;
import com.sap.intern.ecommerce.model.Customer;
import com.sap.intern.ecommerce.model.LoginSession;
import com.sap.intern.ecommerce.repository.CustomerRepository;
import com.sap.intern.ecommerce.service.CustomerService;
import com.sap.intern.ecommerce.service.LoginService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerserviceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoginService loginService;

    @Override
    public Customer findByEmail(@NonNull String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Email or password are incorrect"));
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findByToken(String token) {
        LoginSession loginSession = loginService.validateToken(token);

        return findByEmail(loginSession.getUser().getEmail());

    }
}
