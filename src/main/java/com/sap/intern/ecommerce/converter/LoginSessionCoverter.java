package com.sap.intern.ecommerce.converter;

import com.sap.intern.ecommerce.dto.LoginSessionDTO;
import com.sap.intern.ecommerce.model.LoginSession;
import org.springframework.stereotype.Component;

@Component
public class LoginSessionCoverter extends AbstractConverter<LoginSessionDTO, LoginSession> {

    @Override
    public LoginSession convert(LoginSessionDTO source) {
        return getMapper().map(source, LoginSession.class);
    }
}
