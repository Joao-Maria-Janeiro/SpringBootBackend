package com.sap.intern.ecommerce.converter;

import lombok.Getter;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;


@Getter
public abstract class AbstractConverter<S, T> implements Converter<S, T> {
    private final ModelMapper mapper;

    AbstractConverter() {
        mapper = new ModelMapper();
    }

    @Override
    @NonNull
    public abstract T convert(@NonNull final S source);
}
