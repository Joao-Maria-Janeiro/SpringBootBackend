package com.sap.intern.ecommerce.service;

import com.sap.intern.ecommerce.model.Product;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface ProductService {

    Product save(Product product);

    Page<Product> listAllByPage(Integer pageNumber);

    Product findByName(String name);

    Product findById(Long id);

    Collection<Product> findAllByIds(Long... productIds);
}
