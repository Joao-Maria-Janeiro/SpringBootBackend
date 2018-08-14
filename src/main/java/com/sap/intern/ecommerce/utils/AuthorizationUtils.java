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

package com.sap.intern.ecommerce.utils;

import com.google.common.net.HttpHeaders;
import com.sap.intern.ecommerce.exception.InternalErrorException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class AuthorizationUtils {
    private static final String AUTH_SEPARATOR = ":";
    private static final int AUTH_LIMIT = 2;
    private static final String BASIC = "Basic ";
    private static final String TOKEN = "Token ";

    private AuthorizationUtils() {
    }

    public static Pair<String, String> getUserPassword(HttpServletRequest request) {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.startsWith(authorization, BASIC)) {
            String base64Credentials = StringUtils.trim(authorization.substring(BASIC.length()));
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName(StandardCharsets.UTF_8.name()));

            final String[] values = credentials.split(AUTH_SEPARATOR, AUTH_LIMIT);

            if (ArrayUtils.isEmpty(values) || values.length != 2) {
                throw new InternalErrorException("Invalid authentication type");
            }

            return Pair.of(values[0], values[1]);
        } else {
            throw new InternalErrorException("Invalid authentication type");
        }
    }

    public static String getToken(HttpServletRequest request) {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.startsWith(authorization, TOKEN)) {
            String token = StringUtils.trim(authorization.substring(TOKEN.length()));

            if (StringUtils.isEmpty(token)) {
                throw new InternalErrorException("Invalid Token");
            }

            return token;
        } else {
            throw new InternalErrorException("Invalid authentication type");
        }
    }
}