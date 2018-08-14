package com.sap.intern.ecommerce.converter;

import com.sap.intern.ecommerce.dto.PriceDTO;
import com.sap.intern.ecommerce.dto.ProductDTO;
import com.sap.intern.ecommerce.model.Price;
import com.sap.intern.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReverseProductConverter extends AbstractConverter<ProductDTO, Product> {

    @Autowired
    private ReversePriceConverter priceConverter;

    @Override
    public Product convert(ProductDTO source) {

        Product target = getMapper().map(source, Product.class);

        List<Price> prices = new ArrayList<>();

        for (PriceDTO price : source.getPrices()) {
            prices.add(priceConverter.convert(price));
        }

        target.setPrices(prices);

        return target;
    }
}
