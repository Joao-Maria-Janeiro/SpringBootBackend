package com.sap.intern.ecommerce.converter;

import com.sap.intern.ecommerce.dto.UserDTO;
import com.sap.intern.ecommerce.model.User;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractConverter<User, UserDTO> {
    @Override
    public UserDTO convert(@NonNull final User source) {
        return getMapper().map(source, UserDTO.class);
    }
}

