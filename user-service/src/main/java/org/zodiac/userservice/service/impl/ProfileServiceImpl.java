package org.zodiac.userservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zodiac.userservice.entity.Profile;
import org.zodiac.userservice.entity.ProfileStatus;
import org.zodiac.userservice.entity.SystemRole;
import org.zodiac.userservice.entity.SystemRoleName;
import org.zodiac.userservice.repository.ProfileRepository;
import org.zodiac.userservice.service.ProfileService;
import org.zodiac.userservice.service.SystemRoleService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
	private final ProfileRepository profileRepository;
	private final SystemRoleService systemRoleService;

	@Override
	public Profile save(Profile profile) {
		if (profile.getId() != null) {
			Profile fromDb = profileRepository.findById(profile.getId())
					.orElseThrow(EntityNotFoundException::new);
			profile.setUser(fromDb.getUser());
		}

		profile.setStatus(ProfileStatus.ACTIVE);

		List<SystemRoleName> roleNames = profile.getSystemRoles()
				.stream()
				.map(SystemRole::getName)
				.toList();

		profile = profileRepository.save(profile);

		List<SystemRole> roles = systemRoleService.findByNameIn(roleNames);

		profile.setSystemRoles(roles);

		return profileRepository.save(profile);
	}

	@Override
	public Profile deactivate(String profileId) {
		Profile profile = profileRepository.findById(profileId)
				.orElseThrow(EntityNotFoundException::new)
				.setStatus(ProfileStatus.INACTIVE);

		return profileRepository.save(profile);
	}
}
