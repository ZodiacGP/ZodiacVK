package org.zodiac.common.converter;

import org.mapstruct.MapperConfig;

@MapperConfig(componentModel = "spring")
public interface FromDtoConverter<D, E> {
    E fromDto(D d);
}