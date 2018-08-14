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
import com.sap.intern.ecommerce.model.LoginSession;
import com.sap.intern.ecommerce.model.User;
import com.sap.intern.ecommerce.repository.LoginSessionsRepository;
import com.sap.intern.ecommerce.repository.UserRepository;
import com.sap.intern.ecommerce.service.LoginService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class LoginServiceImpl implements LoginService {

    private static final int DEFAULT_VALUE = 14;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginSessionsRepository loginSessionsRepository;

    @Value("${token.lifetime.days}")
    private String tokenLifetimeDays;

    @Override
    public User checkCredentials(String email, String password) {
        checkNotNull(email);
        checkNotNull(password);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("User not found!"));

        if (!user.getPassword().equals(password)) {
            throw new AuthenticationException("User/Password incorrect!");
        }

        return user;
    }

    @Override
    public String generateToken(String email, String password) {
        final User user = checkCredentials(email, password);
        final Date date = Date.from(Instant.now().plus(NumberUtils.toInt(tokenLifetimeDays, DEFAULT_VALUE), ChronoUnit.DAYS));
        final LoginSession loginSession = new LoginSession();

        loginSession
                .setToken(java.util.UUID.randomUUID().toString())
                .setExpirationDate(date)
                .setUser(user);

        loginSessionsRepository.save(loginSession);

        return loginSession.getToken();
    }

    @Override
    public LoginSession validateToken(String token) {
        LoginSession loginSession = loginSessionsRepository.findByToken(token);

        if (loginSession.getExpirationDate().after(Date.from(Instant.now()))) {
            return loginSession;
        } else {
            throw new IllegalStateException("You session is no longer valid");
        }
    }
}
