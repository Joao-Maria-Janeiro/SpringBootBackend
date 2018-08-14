package com.sap.intern.ecommerce.service;

import com.sap.intern.ecommerce.model.UserType;
import lombok.NonNull;

public interface UserTypeService {

    @NonNull
    UserType findByName(@NonNull final String name);
}
