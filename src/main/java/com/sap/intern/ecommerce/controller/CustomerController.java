package com.sap.intern.ecommerce.controller;

import com.sap.intern.ecommerce.dto.CustomerDTO;
import com.sap.intern.ecommerce.dto.UserTypeDTO;
import com.sap.intern.ecommerce.exception.AuthenticationException;
import com.sap.intern.ecommerce.facade.AuthorizationFacade;
import com.sap.intern.ecommerce.facade.CustomerFacade;
import com.sap.intern.ecommerce.facade.UserFacade;
import com.sap.intern.ecommerce.facade.UserTypeFacade;
import com.sap.intern.ecommerce.model.AuthRole;
import com.sap.intern.ecommerce.utils.AuthorizationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Slf4j
@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private LoginController controller;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private CustomerFacade customerFacade;

    @Autowired
    private UserTypeFacade userTypeFacade;

    @Autowired
    private AuthorizationFacade authorizationFacade;

    @PutMapping(value = "/create")
    public CustomerDTO save(@RequestBody final CustomerDTO customerDTO, HttpServletRequest request) {

        if (!authorizationFacade.checkPermission(AuthorizationUtils.getToken(request), AuthRole.CREATE_CUSTOMER)) {
            throw new AuthenticationException();
        }

        UserTypeDTO userType = userTypeFacade.findByName("CUSTOMER");
        customerDTO.setType(userType);

        return customerFacade.save(customerDTO);
    }

    @GetMapping(value = "/all")
    public Collection<CustomerDTO> getCustomers(HttpServletRequest request) {

        if (!authorizationFacade.checkPermission(AuthorizationUtils.getToken(request), AuthRole.GET_CUSTOMERS)) {
            throw new AuthenticationException();
        }
        return customerFacade.findAll();
    }

}
