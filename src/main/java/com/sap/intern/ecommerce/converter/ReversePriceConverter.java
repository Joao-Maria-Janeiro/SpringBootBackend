package com.sap.intern.ecommerce.converter;

import com.sap.intern.ecommerce.dto.PriceDTO;
import com.sap.intern.ecommerce.model.Price;
import org.springframework.stereotype.Component;

@Component
public class ReversePriceConverter extends AbstractConverter<PriceDTO, Price> {
    @Override
    public Price convert(PriceDTO source) {
        return getMapper().map(source, Price.class);

    }
}
