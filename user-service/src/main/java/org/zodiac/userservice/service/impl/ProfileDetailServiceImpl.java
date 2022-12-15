package org.zodiac.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zodiac.userservice.entity.ProfileDetail;
import org.zodiac.userservice.repository.ProfileDetailRepository;
import org.zodiac.userservice.service.ProfileDetailService;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileDetailServiceImpl implements ProfileDetailService {
	private final ProfileDetailRepository profileDetailRepository;

	@Override
	public ProfileDetail save(ProfileDetail profileDetail) {
		return profileDetailRepository.save(profileDetail);
	}
}
