package com.sap.intern.ecommerce.converter;

import com.sap.intern.ecommerce.dto.CartDTO;
import com.sap.intern.ecommerce.model.Cart;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ReverseCartConverter extends AbstractConverter<CartDTO, Cart> {
    @Override
    public Cart convert(@NonNull final CartDTO source) {
        return getMapper().map(source, Cart.class);
    }
}
