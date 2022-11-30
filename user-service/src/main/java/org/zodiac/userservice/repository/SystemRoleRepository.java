package org.zodiac.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zodiac.userservice.entity.SystemRole;
import org.zodiac.userservice.entity.SystemRoleName;

import java.util.List;

@Repository
public interface SystemRoleRepository extends JpaRepository<SystemRole, String> {
    List<SystemRole> findByNameIn(List<SystemRoleName> names);
}
