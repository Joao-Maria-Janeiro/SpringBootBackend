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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.sap.intern.ecommerce.model.AuthRole;
import com.sap.intern.ecommerce.model.AuthUser;

import java.util.List;

import static com.sap.intern.ecommerce.model.AuthRole.*;
import static com.sap.intern.ecommerce.model.AuthUser.ADMIN;
import static com.sap.intern.ecommerce.model.AuthUser.CUSTOMER;

final class AuthorizationMap {
    static final ImmutableMap<AuthUser, List<AuthRole>> AUTH_MAP;

    static {
        Builder<AuthUser, List<AuthRole>> builder = new Builder<>();

        builder.put(ADMIN, ImmutableList.of(CREATE_CUSTOMER, CREATE_PRODUCT, GET_CUSTOMERS, CREATE_CART, ADD_TO_CART, REMOVE_FROM_CART));
        builder.put(CUSTOMER, ImmutableList.of(CREATE_CART, ADD_TO_CART, REMOVE_FROM_CART));

        AUTH_MAP = builder.build();
    }

    private AuthorizationMap() {
    }
}
