package com.sap.intern.ecommerce.controller;

import com.sap.intern.ecommerce.dto.CustomerDTO;
import com.sap.intern.ecommerce.dto.PageDTO;
import com.sap.intern.ecommerce.dto.ProductDTO;
import com.sap.intern.ecommerce.exception.AuthenticationException;
import com.sap.intern.ecommerce.facade.AuthorizationFacade;
import com.sap.intern.ecommerce.facade.CustomerFacade;
import com.sap.intern.ecommerce.facade.ProductFacade;
import com.sap.intern.ecommerce.facade.UserFacade;
import com.sap.intern.ecommerce.model.AuthRole;
import com.sap.intern.ecommerce.utils.AuthorizationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private ProductFacade productFacade;

    @Autowired
    private CustomerFacade customerFacade;

    @Autowired
    private AuthorizationFacade authorizationFacade;

    @PutMapping(value = "/create", produces = APPLICATION_JSON_VALUE)
    public ProductDTO save(@RequestBody final ProductDTO productDTO, HttpServletRequest request) {

        if (!authorizationFacade.checkPermission(AuthorizationUtils.getToken(request), AuthRole.CREATE_PRODUCT)) {
            throw new AuthenticationException();
        }

        return productFacade.save(productDTO);
    }

    @GetMapping(value = "/page/{pageNumber}")
    public PageDTO getProducts(@PathVariable Integer pageNumber, HttpServletRequest request) {
        CustomerDTO customer = customerFacade.findByToken(AuthorizationUtils.getToken(request));

        return productFacade.listAllByPage(pageNumber, customer);
    }
}
