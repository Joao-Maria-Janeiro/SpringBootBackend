package com.sap.intern.ecommerce.service;

import com.sap.intern.ecommerce.model.User;
import lombok.NonNull;

public interface UserService {

    User findByEmail(@NonNull final String email);

    User save(User user);
}
