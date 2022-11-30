package org.zodiac.userservice.converter;

import org.mapstruct.Mapper;
import org.zodiac.common.converter.BaseConverter;
import org.zodiac.userservice.dto.UserDto;
import org.zodiac.userservice.entity.User;

@Mapper(config = BaseConverter.class, uses = ProfileConverter.class)
public interface UserConverter extends BaseConverter<UserDto, User> {
}
