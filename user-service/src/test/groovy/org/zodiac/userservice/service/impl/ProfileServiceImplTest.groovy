package org.zodiac.userservice.service.impl

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.zodiac.userservice.entity.Profile
import org.zodiac.userservice.entity.ProfileStatus
import org.zodiac.userservice.entity.SystemRole
import org.zodiac.userservice.entity.SystemRoleName
import org.zodiac.userservice.repository.ProfileRepository
import org.zodiac.userservice.service.ProfileService
import org.zodiac.userservice.service.SystemRoleService
import spock.lang.Subject

class ProfileServiceImplTest extends UserServiceSpecification {
    @SpringBean
    ProfileRepository profileRepository = Mock()

    @SpringBean
    SystemRoleService systemRoleService = Mock()

    @Autowired
    @Subject
    ProfileService profileService

    def USER_SYSTEM_ROLE = List.of(new SystemRole().setName(SystemRoleName.USER))
    def userProfile
    def id

    def setup() {
        userProfile = new Profile().setSystemRoles(USER_SYSTEM_ROLE)
        id = UUID.randomUUID().toString()
    }


    def "Should save profile"() {
        given:
        def profileToSave = new Profile().setSystemRoles(USER_SYSTEM_ROLE)

        systemRoleService.findByNameIn(_ as List<SystemRoleName>) >> USER_SYSTEM_ROLE
        profileRepository.save(_ as Profile) >> userProfile.setId(id)

        when:
        def savedProfile = profileService.save(profileToSave)

        then:
        savedProfile.id != null
    }

    def "Should deactivate profile"() {
        given:
        profileRepository.findById(id) >> Optional.of(userProfile.setId(id))
        profileRepository.save(_ as Profile) >> userProfile

        when:
        def deactivatedProfile = profileService.deactivate(id)

        then:
        deactivatedProfile.id == id
        deactivatedProfile.status == ProfileStatus.INACTIVE
    }
}
