package com.sap.intern.ecommerce.service.impl;

import com.sap.intern.ecommerce.model.Cart;
import com.sap.intern.ecommerce.model.Customer;
import com.sap.intern.ecommerce.repository.CartRepository;
import com.sap.intern.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart save(Cart cart) {

        Spliterator<Cart> spliterator = cartRepository.findAllCartsByCustomer(cart.getCustomer().getId()).spliterator();

        List<Cart> carts = StreamSupport.stream(spliterator, false)
                .filter(Cart::getActive)
                .peek(cartModel ->  cartModel.setActive(false))
                .collect(Collectors.toList());

        cartRepository.saveAll(carts);

        cart.setActive(true);

        return cartRepository.save(cart);
    }

    @Override
    public Cart findByCustomer(Customer customer) {
        return cartRepository.findByCustomerByActiveCart(customer.getId());
    }

}
