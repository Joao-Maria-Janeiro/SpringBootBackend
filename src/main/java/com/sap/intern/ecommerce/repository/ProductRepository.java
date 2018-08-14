package com.sap.intern.ecommerce.repository;

import com.sap.intern.ecommerce.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Product findByName(String name);
}
