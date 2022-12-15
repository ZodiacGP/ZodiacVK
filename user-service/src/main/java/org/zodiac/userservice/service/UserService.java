package org.zodiac.userservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.zodiac.userservice.entity.User;

public interface UserService {
	User findById(String id);
	User save(User user);
	Page<User> search(Specification<User> specification, Pageable pageable);
}
