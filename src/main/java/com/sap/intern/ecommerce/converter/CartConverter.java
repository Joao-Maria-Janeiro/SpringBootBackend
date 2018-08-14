package com.sap.intern.ecommerce.converter;

import com.sap.intern.ecommerce.dto.CartDTO;
import com.sap.intern.ecommerce.model.Cart;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CartConverter extends AbstractConverter<Cart, CartDTO> {

    @Override
    public CartDTO convert(@NonNull final Cart source) {

        return getMapper().map(source, CartDTO.class);
    }
}
