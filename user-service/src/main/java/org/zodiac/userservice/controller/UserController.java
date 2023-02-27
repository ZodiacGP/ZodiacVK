package org.zodiac.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.zodiac.userservice.converter.UserConverter;
import org.zodiac.userservice.dto.ProfileDetailDto;
import org.zodiac.userservice.dto.ProfileDto;
import org.zodiac.userservice.dto.UserDto;
import org.zodiac.userservice.entity.ProfileStatus;
import org.zodiac.userservice.entity.User;
import org.zodiac.userservice.search.UserSearchParameters;
import org.zodiac.userservice.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin("https://localhost:4200")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final UserConverter userConverter;

	@GetMapping("/{id}")
	public UserDto getUser(@PathVariable String id) {
		//User user = userService.findById(id);

		//return userConverter.toDto(user);
		return new UserDto()
				.setPassword("pass")
				.setId(id)
				.setUsername("username");
	}

	@PostMapping
	public UserDto addUser(@RequestBody UserDto userDto) {
		User user = userConverter.fromDto(userDto);

		User savedUser = userService.save(user);

		return userConverter.toDto(savedUser);
	}

	@PreAuthorize("hasRole(T(org.zodiac.userservice.entity.SystemRoleName).USER)")
	@GetMapping
	public List<UserDto> findUsers(@ModelAttribute UserSearchParameters searchParameters, Pageable pageable) {
		//return userService.search(searchParameters.toSpecification(), pageable)
		//		.map(userConverter::toDto);
		UserDto userDto1 = new UserDto()
				.setId(UUID.randomUUID().toString())
				.setUsername("username1")
				.setProfile(new ProfileDto().setStatus(ProfileStatus.ACTIVE).setId(UUID.randomUUID().toString()).setProfileDetail(
						new ProfileDetailDto().setEmail("email1@mail.com").setFirstName("Ben").setLastName("Daw")));
		UserDto userDto2 = new UserDto()
				.setId(UUID.randomUUID().toString())
				.setUsername("username2")
				.setProfile(new ProfileDto().setStatus(ProfileStatus.ACTIVE).setId(UUID.randomUUID().toString()).setProfileDetail(
						new ProfileDetailDto().setEmail("email2@mail.com").setFirstName("John").setLastName("Mask")));

		return List.of(userDto1, userDto2);
	}
}
