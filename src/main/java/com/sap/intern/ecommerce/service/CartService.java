package com.sap.intern.ecommerce.service;

import com.sap.intern.ecommerce.model.Cart;
import com.sap.intern.ecommerce.model.Customer;

public interface CartService {
    Cart save(Cart cart);

    Cart findByCustomer(Customer customer);
}
