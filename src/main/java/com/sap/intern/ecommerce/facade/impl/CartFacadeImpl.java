package com.sap.intern.ecommerce.facade.impl;

import com.sap.intern.ecommerce.converter.AbstractConverter;
import com.sap.intern.ecommerce.dto.CartDTO;
import com.sap.intern.ecommerce.dto.CustomerDTO;
import com.sap.intern.ecommerce.dto.IDRequest;
import com.sap.intern.ecommerce.exception.InternalErrorException;
import com.sap.intern.ecommerce.facade.CartFacade;
import com.sap.intern.ecommerce.model.AuthRole;
import com.sap.intern.ecommerce.model.Cart;
import com.sap.intern.ecommerce.model.Customer;
import com.sap.intern.ecommerce.model.Product;
import com.sap.intern.ecommerce.service.AuthorizationService;
import com.sap.intern.ecommerce.service.CartService;
import com.sap.intern.ecommerce.service.CustomerService;
import com.sap.intern.ecommerce.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.*;

@Component
@Slf4j
public class CartFacadeImpl implements CartFacade {

    private final AbstractConverter<Cart, CartDTO> converter;
    private final AbstractConverter<CartDTO, Cart> reverseConverter;
    private final AbstractConverter<CustomerDTO, Customer> customerDTOConverter;
    private final CartService cartService;
    private final ProductService productService;
    private final CustomerService customerService;
    private final AuthorizationService authorizationService;

    @Autowired
    public CartFacadeImpl(AbstractConverter<Cart, CartDTO> converter,
                          AbstractConverter<CartDTO, Cart> reverseConverter,
                          CartService cartService,
                          AbstractConverter<CustomerDTO, Customer> customerDTOConverter,
                          ProductService productService,
                          CustomerService customerService,
                          AuthorizationService authorizationService) {
        this.converter = converter;
        this.reverseConverter = reverseConverter;
        this.cartService = cartService;
        this.customerDTOConverter = customerDTOConverter;
        this.productService = productService;
        this.customerService = customerService;
        this.authorizationService = authorizationService;
    }

    @Override
    public CartDTO save(CartDTO cartDTO) {
        final Cart cart = reverseConverter.convert(cartDTO);

        try {
            return converter.convert(cartService.save(cart));
        } catch (ConstraintViolationException e) {
            log.error("Constraint Violation for category=" + cart.getId(), e);
            throw new InternalErrorException("Duplicate name for " + cart.getId());
        }
    }

    @Override
    public CartDTO save(String token, IDRequest productIds){
        authorizationService.checkPermission(token, AuthRole.CREATE_CART);

        final Customer customer = customerService.findByToken(token);
        final Collection<Product> products = new ArrayList<>();

        for( Long id : productIds.getIds()){
           products.add(productService.findById(id));
        }

        final Cart newCart = new Cart();
        newCart.setActive(true);
        newCart.setCreationDate(new Date());
        newCart.setCustomer(customer);
        newCart.getProducts().addAll(products);

        cartService.save(newCart);
        return converter.convert(newCart);
    }

    @Override
    public CartDTO addProducts(String token, Long... productIds) {
        authorizationService.checkPermission(token, AuthRole.ADD_TO_CART);

        final Customer customer = customerService.findByToken(token);
        final Collection<Product> products = productService.findAllByIds(productIds);

        final Cart cart = Optional.ofNullable(cartService.findByCustomer(customer))
                .map(customerCart -> {
                    customerCart.getProducts().addAll(products);

                    return customerCart;
                })
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setActive(true);
                    newCart.setCreationDate(new Date());
                    newCart.setCustomer(customer);
                    newCart.getProducts().addAll(products);

                    return newCart;
                });

        cartService.save(cart);

        return converter.convert(cart);
    }

    @Override
    public CartDTO removeProduct(String token, Long... productIds){
        authorizationService.checkPermission(token, AuthRole.ADD_TO_CART);

        final Customer customer = customerService.findByToken(token);
        final Collection<Product> products = productService.findAllByIds(productIds);

        final Cart cart = Optional.ofNullable(cartService.findByCustomer(customer))
                .map(customerCart -> {
                    customerCart.getProducts().removeAll(products);

                    return customerCart;
                })
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setActive(true);
                    newCart.setCreationDate(new Date());
                    newCart.setCustomer(customer);
                    newCart.getProducts().addAll(products);

                    return newCart;
                });

        cartService.save(cart);

        return converter.convert(cart);
    }

    @Override
    public CartDTO findByCustomer(CustomerDTO customerDTO) {
        return converter.convert(cartService.findByCustomer(customerDTOConverter.convert(customerDTO)));
    }
}
