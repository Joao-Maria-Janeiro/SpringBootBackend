package com.sap.intern.ecommerce.converter;

import com.sap.intern.ecommerce.dto.ProductDTO;
import com.sap.intern.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductConverter extends AbstractConverter<Product, ProductDTO> {

    @Autowired
    private PriceConverter priceConverter;

    @Override
    public ProductDTO convert(Product source) {

        ProductDTO target = getMapper().map(source, ProductDTO.class);


        target.setPrices(source.getPrices().stream()
                .map(priceConverter::convert)
                .collect(Collectors.toSet()));

        return target;

    }
}
