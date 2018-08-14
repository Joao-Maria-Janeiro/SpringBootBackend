package com.sap.intern.ecommerce.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.intern.ecommerce.SpringContext;
import com.sap.intern.ecommerce.dto.CartDTO;
import com.sap.intern.ecommerce.dto.IDRequest;
import com.sap.intern.ecommerce.facade.CustomerFacade;
import com.sap.intern.ecommerce.repository.CartRepository;
import com.sap.intern.ecommerce.repository.LoginSessionsRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CartControllerTest extends SpringContext {

    @Autowired
    private CustomerFacade customerFacade;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private LoginSessionsRepository sessionsRepository;

    private ObjectMapper mapper;
    private String token;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        token = getToken();
    }

    @Test
    public void save() throws JsonProcessingException {

        ResponseEntity<CartDTO> entity = createCart();

        assertThat(entity, notNullValue());
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(entity.getBody(), notNullValue());
        assertThat(mapToProductIds(entity.getBody()), containsInAnyOrder(6L, 9L));
    }

    @Test
    public void addProduct() throws JsonProcessingException {
        createCart();

        ResponseEntity<CartDTO> entity = restCall(CartDTO.class, HttpMethod.GET, "cart/product/add/12", token);

        assertThat(entity, notNullValue());
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(entity.getBody(), notNullValue());
        assertThat(mapToProductIds(entity.getBody()), containsInAnyOrder(6L, 9L, 12L));
    }

    @Test
    public void addProduct_no_cart_created() {
        ResponseEntity<CartDTO> entity = restCall(CartDTO.class, HttpMethod.GET, "cart/product/add/6", token);

        assertThat(entity, notNullValue());
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(entity.getBody(), notNullValue());
        assertThat(mapToProductIds(entity.getBody()), containsInAnyOrder(6L));
    }


    @Test
    public void removeProduct() throws JsonProcessingException {
        createCart();

        ResponseEntity<CartDTO> responseEntity = restCall(CartDTO.class, HttpMethod.GET, "cart/product/remove/9", token);

        assertThat(responseEntity, notNullValue());
        assertThat(responseEntity.getBody(), notNullValue());
        assertThat(responseEntity.getBody().getCustomer(), is(customerFacade.findByToken(token)));
        assertThat(mapToProductIds(responseEntity.getBody()), containsInAnyOrder(6L));
    }

    @Test
    public void getCart() throws JsonProcessingException {
        createCart();

        ResponseEntity<CartDTO> responseEntity = restCall(CartDTO.class, HttpMethod.GET, "/cart/get/active", token);

        assertThat(responseEntity, notNullValue());
        assertThat(responseEntity.getBody(), notNullValue());
        assertThat(responseEntity.getBody().getCustomer(), is(customerFacade.findByToken(token)));
        assertThat(mapToProductIds(responseEntity.getBody()), containsInAnyOrder(6L, 9L));
    }

    private ResponseEntity<CartDTO> createCart() throws JsonProcessingException {
        IDRequest request = new IDRequest();
        request.getIds().add(6L);
        request.getIds().add(9L);

        return restCall(CartDTO.class, HttpMethod.PUT, "cart/create", token, mapper.writeValueAsString(request));
    }
}
