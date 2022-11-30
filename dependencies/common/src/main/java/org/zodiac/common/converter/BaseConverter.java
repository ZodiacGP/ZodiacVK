package org.zodiac.common.converter;

import org.mapstruct.MapperConfig;

@MapperConfig(componentModel = "spring")
public interface BaseConverter<D, E> extends ToDtoConverter<E, D>, FromDtoConverter<D, E> {
}