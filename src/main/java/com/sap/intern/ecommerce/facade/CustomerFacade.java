package com.sap.intern.ecommerce.facade;

import com.sap.intern.ecommerce.dto.CustomerDTO;
import lombok.NonNull;

import java.util.Collection;

public interface CustomerFacade {

    @NonNull
    Collection<CustomerDTO> findAll();

    @NonNull
    CustomerDTO save(CustomerDTO customer);

    @NonNull
    CustomerDTO findByEmail(String email);

    CustomerDTO findByToken(String token);
}
