package org.zodiac.userservice.service.impl

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.zodiac.userservice.entity.SystemRole
import org.zodiac.userservice.entity.SystemRoleName
import org.zodiac.userservice.repository.SystemRoleRepository
import org.zodiac.userservice.service.SystemRoleService
import spock.lang.Subject

class SystemRoleServiceImplTest extends UserServiceSpecification {
    @SpringBean
    SystemRoleRepository systemRoleRepository = Mock()

    @Autowired
    @Subject
    SystemRoleService systemRoleService

    def USER_SYSTEM_ROLE = new SystemRole().setName(SystemRoleName.USER)
    def ADMIN_SYSTEM_ROLE = new SystemRole().setName(SystemRoleName.ADMIN)
    def systemRoleNames = List.of(SystemRoleName.USER, SystemRoleName.ADMIN)

    def "Should find roles by names"() {
        given:
        def roles = List.of(USER_SYSTEM_ROLE, ADMIN_SYSTEM_ROLE)

        systemRoleRepository.findByNameIn(_ as List<SystemRoleName>) >> roles

        when:
        def foundedRoles = systemRoleService.findByNameIn(systemRoleNames)

        then:
        foundedRoles.size() == 2
        foundedRoles.contains(USER_SYSTEM_ROLE)
        foundedRoles.contains(ADMIN_SYSTEM_ROLE)
    }
}
