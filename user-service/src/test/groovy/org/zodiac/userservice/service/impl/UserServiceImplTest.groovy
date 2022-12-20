package org.zodiac.userservice.service.impl

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.zodiac.userservice.entity.Profile
import org.zodiac.userservice.entity.SystemRole
import org.zodiac.userservice.entity.SystemRoleName
import org.zodiac.userservice.entity.User
import org.zodiac.userservice.repository.UserRepository
import org.zodiac.userservice.service.ProfileService
import org.zodiac.userservice.service.UserService
import spock.lang.Subject

class UserServiceImplTest extends UserServiceSpecification {
    @SpringBean
    UserRepository userRepository = Mock()

    @SpringBean
    ProfileService profileService = Mock()

    @Autowired
    @Subject
    UserService userService

    def USER_SYSTEM_ROLE = List.of(new SystemRole().setName(SystemRoleName.USER))
    def userProfile
    def user
    def id

    def PASSWORD = "password"
    def USERNAME = "user"

    def setup() {
        userProfile = new Profile().setSystemRoles(USER_SYSTEM_ROLE)
        user = new User().setUsername(USERNAME).setPassword(PASSWORD).setProfile(userProfile)
        id = getRandomId()
    }

    def "Should save user"() {
        given:
        def userToSave = new User().setUsername(USERNAME).setPassword(PASSWORD).setProfile(userProfile)

        userRepository.save(_ as User) >> user.setId(id)
        profileService.save(_ as Profile) >> userProfile.setId(id)

        when:
        def savedUser = userService.save(userToSave)

        then:
        savedUser.id != null
        savedUser.username == USERNAME
        savedUser.password == PASSWORD
        savedUser.profile == userProfile
    }

    def "Should find user by id"() {
        given:
        userRepository.findById(id) >> Optional.of(user.setId(id))

        when:
        def foundedUser = userService.findById(id)

        then:
        foundedUser.id != null
        foundedUser.username == USERNAME
        foundedUser.password == PASSWORD
        foundedUser.profile == userProfile
    }

    def "Should find users"() {
        given:
        Specification<User> specification = Mock()
        Pageable pageable = Mock()

        userRepository.findAll(_ as Specification, _ as Pageable) >> new PageImpl<User>(List.of(user.setId(id)))

        when:
        def foundedUsers = userService.search(specification, pageable)

        then:
        foundedUsers != null
        !foundedUsers.isEmpty()
        foundedUsers.size == 1
    }
}
