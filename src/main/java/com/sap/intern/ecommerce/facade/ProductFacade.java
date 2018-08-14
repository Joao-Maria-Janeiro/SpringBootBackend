package com.sap.intern.ecommerce.facade;

import com.sap.intern.ecommerce.dto.CustomerDTO;
import com.sap.intern.ecommerce.dto.PageDTO;
import com.sap.intern.ecommerce.dto.ProductDTO;
import lombok.NonNull;

import java.util.Set;

public interface ProductFacade {

    @NonNull
    ProductDTO save(ProductDTO productDTO);

    @NonNull
    PageDTO listAllByPage(Integer pageNumber, CustomerDTO customerDTO);

    Set<ProductDTO> findAllProductsById(Set<Long> idList);

    ProductDTO findByName(String name);

    ProductDTO findById(Long id);

}
