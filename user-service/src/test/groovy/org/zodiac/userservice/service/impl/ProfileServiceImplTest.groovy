package org.zodiac.userservice.service.impl

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.zodiac.userservice.entity.*
import org.zodiac.userservice.repository.ProfileRepository
import org.zodiac.userservice.service.ProfileDetailService
import org.zodiac.userservice.service.ProfileService
import org.zodiac.userservice.service.SystemRoleService
import spock.lang.Subject

class ProfileServiceImplTest extends UserServiceSpecification {
    @SpringBean
    ProfileRepository profileRepository = Mock()

    @SpringBean
    SystemRoleService systemRoleService = Mock()

    @SpringBean
    ProfileDetailService profileDetailService = Mock()

    @Autowired
    @Subject
    ProfileService profileService

    def USER_SYSTEM_ROLE = List.of(new SystemRole().setName(SystemRoleName.USER))
    def userProfile
    def profileDetail
    def id

    def setup() {
        profileDetail = new ProfileDetail().setFirstName("test").setLastName("test")
        userProfile = new Profile().setSystemRoles(USER_SYSTEM_ROLE)
        id = getRandomId()
    }

    def "Should save profile"() {
        given:
        def profileToSave = new Profile().setSystemRoles(USER_SYSTEM_ROLE).setProfileDetail(profileDetail)

        systemRoleService.findByNameIn(_ as List<SystemRoleName>) >> USER_SYSTEM_ROLE
        profileDetailService.save(_ as ProfileDetail) >> profileDetail.setId(getRandomId())
        profileRepository.save(_ as Profile) >> userProfile.setId(id)

        when:
        def savedProfile = profileService.save(profileToSave)

        then:
        savedProfile.id != null
        savedProfile.id == id
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
