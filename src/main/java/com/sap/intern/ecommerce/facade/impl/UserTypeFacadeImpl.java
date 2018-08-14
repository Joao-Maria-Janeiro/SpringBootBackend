package com.sap.intern.ecommerce.facade.impl;

import com.sap.intern.ecommerce.converter.AbstractConverter;
import com.sap.intern.ecommerce.dto.UserTypeDTO;
import com.sap.intern.ecommerce.facade.UserTypeFacade;
import com.sap.intern.ecommerce.model.UserType;
import com.sap.intern.ecommerce.service.UserTypeService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserTypeFacadeImpl implements UserTypeFacade {

    private final AbstractConverter<UserType, UserTypeDTO> userConverter;

    @Autowired
    UserTypeService typeService;

    @Autowired
    public UserTypeFacadeImpl(AbstractConverter<UserType, UserTypeDTO> userConverter) {
        this.userConverter = userConverter;
    }


    @NonNull
    @Override
    public UserTypeDTO findByName(String name) {
        return userConverter.convert(typeService.findByName(name));
    }
}
