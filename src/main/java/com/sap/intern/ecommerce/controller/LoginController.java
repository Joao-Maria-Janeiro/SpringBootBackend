package com.sap.intern.ecommerce.controller;

import com.sap.intern.ecommerce.dto.UserDTO;
import com.sap.intern.ecommerce.facade.LoginFacade;
import com.sap.intern.ecommerce.facade.UserFacade;
import com.sap.intern.ecommerce.utils.AuthorizationUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private LoginFacade loginFacade;

    @RequestMapping("/login")
    public UserDTO login(HttpServletRequest request) {
        Pair<String, String> credentials = AuthorizationUtils.getUserPassword(request);

        return loginFacade.checkCredentials(credentials.getKey(), credentials.getValue());
    }

    @RequestMapping("/token")
    public String token(HttpServletRequest request) {
        Pair<String, String> credentials = AuthorizationUtils.getUserPassword(request);

        return loginFacade.generateToken(credentials.getKey(), credentials.getValue());
    }

}
