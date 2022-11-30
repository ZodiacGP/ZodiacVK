package org.zodiac.userservice.service;

import org.zodiac.userservice.entity.SystemRole;
import org.zodiac.userservice.entity.SystemRoleName;

import java.util.List;

public interface SystemRoleService {
    List<SystemRole> findByNameIn(List<SystemRoleName> names);
}
