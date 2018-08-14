package com.sap.intern.ecommerce.service.impl;

import com.sap.intern.ecommerce.exception.NotFoundException;
import com.sap.intern.ecommerce.model.User;
import com.sap.intern.ecommerce.repository.UserRepository;
import com.sap.intern.ecommerce.service.UserService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findByEmail(@NonNull String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Username or password are incorrect"));
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }
}
