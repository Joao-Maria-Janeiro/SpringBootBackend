package com.sap.intern.ecommerce.facade.impl;

import com.sap.intern.ecommerce.converter.AbstractConverter;
import com.sap.intern.ecommerce.dto.CustomerDTO;
import com.sap.intern.ecommerce.exception.InternalErrorException;
import com.sap.intern.ecommerce.facade.CustomerFacade;
import com.sap.intern.ecommerce.model.Customer;
import com.sap.intern.ecommerce.model.LoginSession;
import com.sap.intern.ecommerce.service.CustomerService;
import com.sap.intern.ecommerce.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Component
public class CustomerFacadeImpl implements CustomerFacade {

    private AbstractConverter<Customer, CustomerDTO> converter;
    private AbstractConverter<CustomerDTO, Customer> reverseConverter;
    private CustomerService customerService;
    private LoginService loginService;

    @Autowired
    public CustomerFacadeImpl(CustomerService customerService,
                              AbstractConverter<Customer, CustomerDTO> converter,
                              AbstractConverter<CustomerDTO, Customer> reverseConverter, LoginService loginService) {
        this.converter = converter;
        this.reverseConverter = reverseConverter;
        this.customerService = customerService;
        this.loginService = loginService;
    }


    @Override
    public Collection<CustomerDTO> findAll() {
        return StreamSupport.stream(customerService.findAll().spliterator(), true)
                .map(converter::convert)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(CustomerDTO::getName))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        final Customer customer = reverseConverter.convert(customerDTO);
        try {
            return converter.convert(customerService.save(customer));
        } catch (ConstraintViolationException e) {
            log.error("Constraint Violation for category=" + customer.getName(), e);
            throw new InternalErrorException("Duplicate name for " + customer.getName());
        }
    }

    @Override
    public CustomerDTO findByEmail(String email) {
        return converter.convert(customerService.findByEmail(email));
    }

    @Override
    public CustomerDTO findByToken(String token) {
        LoginSession loginSession = loginService.validateToken(token);

        return converter.convert(customerService.findByEmail(loginSession.getUser().getEmail()));

    }
}