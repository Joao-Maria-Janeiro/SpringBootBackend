package com.sap.intern.ecommerce.facade;

import com.sap.intern.ecommerce.dto.UserTypeDTO;
import lombok.NonNull;

public interface UserTypeFacade {

    @NonNull
    UserTypeDTO findByName(@NonNull final String name);
}
