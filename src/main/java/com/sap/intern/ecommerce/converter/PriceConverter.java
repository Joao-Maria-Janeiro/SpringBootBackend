package com.sap.intern.ecommerce.converter;

import com.sap.intern.ecommerce.dto.PriceDTO;
import com.sap.intern.ecommerce.model.Price;
import org.springframework.stereotype.Component;

@Component
public class PriceConverter extends AbstractConverter<Price, PriceDTO> {

    @Override
    public PriceDTO convert(Price source) {
        return getMapper().map(source, PriceDTO.class);
    }
}
