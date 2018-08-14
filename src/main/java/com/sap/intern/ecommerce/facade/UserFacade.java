package com.sap.intern.ecommerce.facade;

import com.sap.intern.ecommerce.dto.UserDTO;
import lombok.NonNull;

public interface UserFacade {

    @NonNull
    UserDTO findByEmail(@NonNull final String email);
}
