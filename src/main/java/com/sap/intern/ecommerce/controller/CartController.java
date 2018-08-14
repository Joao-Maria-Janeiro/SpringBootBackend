package com.sap.intern.ecommerce.controller;

import com.sap.intern.ecommerce.dto.CartDTO;
import com.sap.intern.ecommerce.dto.CustomerDTO;
import com.sap.intern.ecommerce.dto.IDRequest;
import com.sap.intern.ecommerce.dto.ProductDTO;
import com.sap.intern.ecommerce.exception.AuthenticationException;
import com.sap.intern.ecommerce.facade.AuthorizationFacade;
import com.sap.intern.ecommerce.facade.CartFacade;
import com.sap.intern.ecommerce.facade.CustomerFacade;
import com.sap.intern.ecommerce.facade.ProductFacade;
import com.sap.intern.ecommerce.model.AuthRole;
import com.sap.intern.ecommerce.utils.AuthorizationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequestMapping("/cart")
@RestController
public class CartController {

    @Autowired
    private AuthorizationFacade authorizationFacade;

    @Autowired
    private CustomerFacade customerFacade;

    @Autowired
    private CartFacade cartFacade;

    @Autowired
    private ProductFacade productFacade;


    @PutMapping(value = "/create")
    public CartDTO save(@RequestBody final IDRequest productIds, HttpServletRequest request) {

        log.info("Saving cart...");

        final String token = AuthorizationUtils.getToken(request);

        return cartFacade.save(token, productIds);
    }

    @GetMapping(value = "/product/add/{id}")
    public CartDTO addProduct(@PathVariable final Long id, final HttpServletRequest request) {
        final String token = AuthorizationUtils.getToken(request);

        return cartFacade.addProducts(token, id);
    }

    @GetMapping(value = "/product/remove/{id}")
    public CartDTO removeProduct(@PathVariable Long id, HttpServletRequest request) {
        final String token = AuthorizationUtils.getToken(request);

        return cartFacade.removeProduct(token, id);
    }

    @GetMapping(value = "/get/active")
    public CartDTO loadActiveCart(HttpServletRequest request) {
        String token = AuthorizationUtils.getToken(request);

        if (!authorizationFacade.checkPermission(token, AuthRole.ADD_TO_CART)) {
            throw new AuthenticationException();
        }
        
        return cartFacade.findByCustomer(customerFacade.findByToken(token));
    }
}
