package org.zodiac.userservice.service;

import org.zodiac.userservice.entity.User;

public interface UserService {
	User findById(String id);
	User save(User user);
}
