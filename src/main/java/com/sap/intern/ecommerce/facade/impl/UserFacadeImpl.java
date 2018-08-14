package com.sap.intern.ecommerce.facade.impl;

import com.sap.intern.ecommerce.converter.AbstractConverter;
import com.sap.intern.ecommerce.dto.UserDTO;
import com.sap.intern.ecommerce.facade.UserFacade;
import com.sap.intern.ecommerce.model.User;
import com.sap.intern.ecommerce.service.UserService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserFacadeImpl implements UserFacade {

    private final AbstractConverter<User, UserDTO> userConverter;


    @Autowired
    UserService userService;

    public UserFacadeImpl(AbstractConverter<User, UserDTO> userConverter) {
        this.userConverter = userConverter;
    }


    @NonNull
    @Override
    public UserDTO findByEmail(String email) {
        return userConverter.convert(userService.findByEmail(email));
    }
}
