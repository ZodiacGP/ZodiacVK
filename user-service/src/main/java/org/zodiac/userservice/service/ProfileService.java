package org.zodiac.userservice.service;

import org.zodiac.userservice.entity.Profile;

public interface ProfileService {
	Profile save(Profile profile);

	Profile deactivate(String profileId);
}
