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

package com.sap.intern.ecommerce.service.impl;

import com.sap.intern.ecommerce.exception.AuthenticationException;
import com.sap.intern.ecommerce.model.AuthRole;
import com.sap.intern.ecommerce.model.AuthUser;
import com.sap.intern.ecommerce.model.LoginSession;
import com.sap.intern.ecommerce.model.User;
import com.sap.intern.ecommerce.service.AuthorizationService;
import com.sap.intern.ecommerce.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean hasPermission(final User user, final AuthRole authRole) {
        // TODO refactoring to uses Repository instead access directly
        AuthUser authUser = AuthUser.valueOf(user.getType().getName());

        return AuthorizationMap.AUTH_MAP.get(authUser).stream()
                .anyMatch(role -> role == authRole);
    }

    @Override
    public void checkPermission(String token, AuthRole authRole) {
        final LoginSession validSession = loginService.validateToken(token);

        if (!hasPermission(validSession.getUser(), authRole)) {
            throw new AuthenticationException();
        }
    }
}
