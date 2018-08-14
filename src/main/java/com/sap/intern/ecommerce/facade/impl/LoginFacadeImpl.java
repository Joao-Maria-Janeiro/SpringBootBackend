/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */

package com.sap.intern.ecommerce.facade.impl;

import com.sap.intern.ecommerce.converter.AbstractConverter;
import com.sap.intern.ecommerce.dto.UserDTO;
import com.sap.intern.ecommerce.facade.LoginFacade;
import com.sap.intern.ecommerce.model.User;
import com.sap.intern.ecommerce.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginFacadeImpl implements LoginFacade {
    @Autowired
    private LoginService loginService;

    @Autowired
    private AbstractConverter<User, UserDTO> userConverter;

    @Override
    public UserDTO checkCredentials(String email, String password) {
        User user = loginService.checkCredentials(email, password);

        return userConverter.convert(user);
    }

    @Override
    public String generateToken(String email, String password) {
        return loginService.generateToken(email, password);
    }

    @Override
    public void validateToken(String token) {
        // TODO
        loginService.validateToken(token);
    }
}
