package com.sap.intern.ecommerce.facade;

import com.sap.intern.ecommerce.dto.CartDTO;
import com.sap.intern.ecommerce.dto.CustomerDTO;
import com.sap.intern.ecommerce.dto.IDRequest;

public interface CartFacade {
    CartDTO save(CartDTO cartDTO);


    CartDTO save(String token, IDRequest productIds);

    CartDTO addProducts(String token, Long... productIds);

    CartDTO removeProduct(String token, Long... productIds);

    CartDTO findByCustomer(CustomerDTO customerDTO);
}
