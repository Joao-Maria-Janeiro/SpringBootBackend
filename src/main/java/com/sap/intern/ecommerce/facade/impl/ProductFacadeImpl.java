package com.sap.intern.ecommerce.facade.impl;

import com.sap.intern.ecommerce.converter.AbstractConverter;
import com.sap.intern.ecommerce.dto.*;
import com.sap.intern.ecommerce.exception.InternalErrorException;
import com.sap.intern.ecommerce.facade.ProductFacade;
import com.sap.intern.ecommerce.model.Product;
import com.sap.intern.ecommerce.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProductFacadeImpl implements ProductFacade {

    private AbstractConverter<Product, ProductDTO> converter;
    private AbstractConverter<ProductDTO, Product> reverseConverter;
    private ProductService productService;

    @Autowired
    public ProductFacadeImpl(AbstractConverter<ProductDTO, Product> reverseConverter,
                             AbstractConverter<Product, ProductDTO> converter,
                             ProductService productService) {
        this.reverseConverter = reverseConverter;
        this.converter = converter;
        this.productService = productService;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        final Product product = reverseConverter.convert(productDTO);
        try {
            return converter.convert(productService.save(product));
        } catch (ConstraintViolationException e) {
            throw new InternalErrorException("Duplicate name for " + product);
        }
    }

    @Override
    public PageDTO listAllByPage(Integer pageNumber, CustomerDTO customerDTO) {

        Page<Product> entityPage = productService.listAllByPage(pageNumber);

        List<Product> products = entityPage.getContent();

        List<ProductDTO> dtos = products.parallelStream()
                .map(converter::convert)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(ProductDTO::getName))
                .collect(Collectors.toList());

        Collection<ProductGetDTO> correctCurrency = new ArrayList<>();

        for (ProductDTO product : dtos) {
            for (PriceDTO price : product.getPrices()) {
                if (price.getCurrencyCode().equals(customerDTO.getCountryISO())) {
                    correctCurrency.add(new ProductGetDTO()
                            .setDescription(product.getDescription())
                            .setName(product.getName())
                            .setPrice(price)
                            .setId(product.getId()));
                }
            }
        }

        return new PageDTO()
                .setItems(correctCurrency)
                .setCurrentPage(pageNumber)
                .setTotalItems(entityPage.getTotalElements())
                .setPageSize(entityPage.getPageable().getPageSize());
    }

    @Override
    public Set<ProductDTO> findAllProductsById(Set<Long> idList) {
        Set<ProductDTO> productDTOS = new HashSet<>();

        for (Long id : idList) {
            productDTOS.add(findById(id));
        }

        return productDTOS;
    }

    @Override
    public ProductDTO findByName(String name) {
        return converter.convert(productService.findByName(name));
    }

    @Override
    public ProductDTO findById(Long id) {
        return converter.convert(productService.findById(id));
    }


}
