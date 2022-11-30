package org.zodiac.userservice.converter;

import org.mapstruct.Mapper;
import org.zodiac.common.converter.BaseConverter;
import org.zodiac.userservice.entity.SystemRole;
import org.zodiac.userservice.entity.SystemRoleName;

@Mapper(config = BaseConverter.class)
public abstract class SystemRoleNameConverter implements BaseConverter<SystemRoleName, SystemRole> {
	@Override
	public SystemRole fromDto(SystemRoleName systemRoleName) {
		return new SystemRole().setName(systemRoleName);
	}

	@Override
	public SystemRoleName toDto(SystemRole systemRole) {
		return systemRole.getName();
	}
}
