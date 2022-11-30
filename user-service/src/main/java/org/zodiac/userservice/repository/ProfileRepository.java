package org.zodiac.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zodiac.userservice.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
}
