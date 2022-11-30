package org.zodiac.common.converter;

import org.mapstruct.MapperConfig;

@MapperConfig(componentModel = "spring")
public interface ToDtoConverter<E, D> {
    D toDto(E e);
}