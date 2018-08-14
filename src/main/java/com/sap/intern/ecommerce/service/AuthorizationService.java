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

package com.sap.intern.ecommerce.service;

import com.sap.intern.ecommerce.model.AuthRole;
import com.sap.intern.ecommerce.model.User;

public interface AuthorizationService {
    boolean hasPermission(User user, AuthRole authRole);

    void checkPermission(String token, AuthRole authRole);
}
