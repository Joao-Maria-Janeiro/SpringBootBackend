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

import com.sap.intern.ecommerce.facade.AuthorizationFacade;
import com.sap.intern.ecommerce.model.AuthRole;
import com.sap.intern.ecommerce.service.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorizationFacadeImpl implements AuthorizationFacade {

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public boolean checkPermission(String token, AuthRole authRole) {
        boolean valid = false;

        try {
            authorizationService.checkPermission(token, authRole);
            valid = true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return valid;
    }
}
