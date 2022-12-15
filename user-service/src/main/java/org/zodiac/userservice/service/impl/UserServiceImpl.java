package org.zodiac.userservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zodiac.userservice.entity.User;
import org.zodiac.userservice.repository.UserRepository;
import org.zodiac.userservice.service.ProfileService;
import org.zodiac.userservice.service.UserService;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final ProfileService profileService;
	private final PasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	@Override
	public User findById(String id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s wasn't found", id)));
	}

	@Override
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser = userRepository.save(user);

		profileService.save(user.getProfile().setUser(savedUser));

		return userRepository.save(savedUser);
	}

	@Override
	public Page<User> search(Specification<User> specification, Pageable pageable) {
		return userRepository.findAll(specification, pageable);
	}
}
