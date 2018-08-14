package com.sap.intern.ecommerce.controllers;

import com.sap.intern.ecommerce.SpringContext;
import com.sap.intern.ecommerce.dto.PageDTO;
import com.sap.intern.ecommerce.dto.ProductGetDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@Slf4j
public class ProductControllerTest extends SpringContext {

    @Test
    public void getProductsByPage() {
        String token = getToken();

        ResponseEntity<PageDTO> responseEntity = restCall(PageDTO.class, HttpMethod.GET, "/product/page/0", token);

        assertThat(responseEntity, notNullValue());
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));

        PageDTO body = responseEntity.getBody();

        assertThat(body, notNullValue());

        assertThat(body.getCurrentPage(), is(0));
        assertThat(body.getTotalItems(), is(3L));
        assertThat(body.getPageSize(), is(2));
        assertThat(body.getItems().size(), is(2));
        assertThat(body.getItems().stream()
                .map(ProductGetDTO::getId)
                .collect(Collectors.toList()), containsInAnyOrder(6L, 9L));

        log.info("Items: {}", body.getItems());
    }
}
