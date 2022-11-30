package org.zodiac.userservice.converter;

import org.mapstruct.Mapper;
import org.zodiac.common.converter.BaseConverter;
import org.zodiac.userservice.dto.ProfileDto;
import org.zodiac.userservice.entity.Profile;
import org.zodiac.userservice.entity.SystemRoleName;

@Mapper(config = BaseConverter.class, uses = SystemRoleNameConverter.class)
public interface ProfileConverter extends BaseConverter<ProfileDto, Profile> {
}
