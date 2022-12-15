package org.zodiac.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zodiac.userservice.entity.ProfileDetail;

@Repository
public interface ProfileDetailRepository extends JpaRepository<ProfileDetail, String> {
}
