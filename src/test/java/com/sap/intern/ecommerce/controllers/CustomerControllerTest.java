package com.sap.intern.ecommerce.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.intern.ecommerce.SpringContext;
import com.sap.intern.ecommerce.dto.CustomerDTO;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class CustomerControllerTest extends SpringContext {

    @Test
    public void getCustomers() {
        ResponseEntity<Collection> entity = restCall(Collection.class, HttpMethod.GET, "customer/all", getTokenAdmin());

        assertThat(entity.getStatusCode(), notNullValue());
    }

    @Test
    public void createCustomer() throws JsonProcessingException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCountryISO("br");
        customerDTO.setName("chris");
        customerDTO.setEmail("chris@email.com");

        ObjectMapper mapper = new ObjectMapper();

        ResponseEntity<CustomerDTO> entity = restCall(CustomerDTO.class, HttpMethod.PUT, "customer/create", getTokenAdmin(), mapper.writeValueAsString(customerDTO));

        assertThat(entity.getStatusCode(), notNullValue());
        assertThat(entity.getBody(), notNullValue());
        assertThat(entity.getBody().getName(), is("chris"));
    }
}
