package org.zodiac.userservice.service.impl

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.zodiac.userservice.entity.ProfileDetail
import org.zodiac.userservice.repository.ProfileDetailRepository
import org.zodiac.userservice.service.ProfileDetailService
import spock.lang.Subject

class ProfileDetailServiceImplTest extends UserServiceSpecification {
    @SpringBean
    ProfileDetailRepository profileDetailRepository = Mock()

    @Autowired
    @Subject
    ProfileDetailService profileDetailService

    def profileDetail
    def id

    def setup() {
        profileDetail = new ProfileDetail().setFirstName("test").setLastName("test").setEmail("test@mail.com")
        id = getRandomId()
    }

    def "Should save profile detail"() {
        given:
        def profileDetailToSave = new ProfileDetail().setFirstName("test").setLastName("test").setEmail("test@mail.com")

        profileDetailRepository.save(_ as ProfileDetail) >> profileDetail.setId(id)

        when:
        def savedProfileDetail = profileDetailService.save(profileDetailToSave)

        then:
        savedProfileDetail.id != null
        savedProfileDetail.id == id
        savedProfileDetail.firstName == "test"
        savedProfileDetail.lastName == "test"
        savedProfileDetail.email == "test@mail.com"
    }
}
