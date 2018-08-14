package com.sap.intern.ecommerce.converter;

import com.sap.intern.ecommerce.dto.UserTypeDTO;
import com.sap.intern.ecommerce.model.UserType;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserTypeConverter extends AbstractConverter<UserType, UserTypeDTO> {
    @Override
    public UserTypeDTO convert(@NonNull final UserType source) {
        return getMapper().map(source, UserTypeDTO.class);
    }
}
