package org.zodiac.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.zodiac.userservice.converter.UserConverter;
import org.zodiac.userservice.dto.UserDto;
import org.zodiac.userservice.entity.User;
import org.zodiac.userservice.search.UserSearchParameters;
import org.zodiac.userservice.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("https://localhost:4200")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final UserConverter userConverter;

	@GetMapping("/{id}")
	public UserDto getUser(@PathVariable String id) {
		User user = userService.findById(id);

		return userConverter.toDto(user);
	}

	@PostMapping
	public UserDto addUser(@RequestBody UserDto userDto) {
		User user = userConverter.fromDto(userDto);

		User savedUser = userService.save(user);

		return userConverter.toDto(savedUser);
	}

	@GetMapping
	public Page<UserDto> findUsers(@ModelAttribute UserSearchParameters searchParameters, Pageable pageable) {
		return userService.search(searchParameters.toSpecification(), pageable)
				.map(userConverter::toDto);
	}
}
