package org.zodiac.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zodiac.userservice.entity.SystemRole;
import org.zodiac.userservice.entity.SystemRoleName;
import org.zodiac.userservice.repository.SystemRoleRepository;
import org.zodiac.userservice.service.SystemRoleService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SystemRoleServiceImpl implements SystemRoleService {
    private final SystemRoleRepository systemRoleRepository;

    @Transactional(readOnly = true)
    @Override
    public List<SystemRole> findByNameIn(List<SystemRoleName> names) {
        return systemRoleRepository.findByNameIn(names);
    }
}
