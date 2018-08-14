package com.sap.intern.ecommerce.service.impl;

import com.sap.intern.ecommerce.model.UserType;
import com.sap.intern.ecommerce.repository.UserTypeRepository;
import com.sap.intern.ecommerce.service.UserTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Override
    public UserType findByName(String name) {
        return userTypeRepository.findByName(name);
    }
}
