package com.sap.intern.ecommerce.converter;

import com.sap.intern.ecommerce.dto.CustomerDTO;
import com.sap.intern.ecommerce.model.Customer;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ReverseCustomerConverter extends AbstractConverter<CustomerDTO, Customer> {
    @Override
    public Customer convert(@NonNull final CustomerDTO source) {
        return getMapper().map(source, Customer.class);
    }
}
