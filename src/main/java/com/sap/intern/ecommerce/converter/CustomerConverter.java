package com.sap.intern.ecommerce.converter;

import com.sap.intern.ecommerce.dto.CustomerDTO;
import com.sap.intern.ecommerce.model.Customer;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter extends AbstractConverter<Customer, CustomerDTO> {
    @Override
    public CustomerDTO convert(@NonNull final Customer source) {

        CustomerDTO target = getMapper().map(source, CustomerDTO.class);

        target.setCountryISO(source.getCountryISO());

        return target;
    }
}

