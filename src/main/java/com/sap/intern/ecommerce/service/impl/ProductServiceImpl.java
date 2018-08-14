package com.sap.intern.ecommerce.service.impl;

import com.google.common.collect.Lists;
import com.sap.intern.ecommerce.exception.NotFoundException;
import com.sap.intern.ecommerce.model.Product;
import com.sap.intern.ecommerce.repository.ProductRepository;
import com.sap.intern.ecommerce.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    @Value("${products.page.size}")
    private Integer pageSize;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Page<Product> listAllByPage(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return productRepository.findAll(pageable);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Not Found"));
    }

    @Override
    public Collection<Product> findAllByIds(Long... productIds) {
        return Lists.newArrayList(productRepository.findAllById(Lists.newArrayList(productIds)));
    }
}
