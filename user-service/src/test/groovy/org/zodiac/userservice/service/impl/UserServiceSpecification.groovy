package org.zodiac.userservice.service.impl

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.zodiac.userservice.UserServiceApplication
import spock.lang.Specification

@ContextConfiguration(classes = UserServiceApplication.class)
@SpringBootTest
class UserServiceSpecification extends Specification {
    protected String getRandomId() {
        UUID.randomUUID().toString()
    }
}
