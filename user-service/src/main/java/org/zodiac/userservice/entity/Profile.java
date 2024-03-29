package org.zodiac.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.zodiac.database.BaseEntity;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Profile extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@OneToOne
	@JoinColumn(name = "profile_detail_id", referencedColumnName = "id")
	private ProfileDetail profileDetail;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "profile_system_role",
			joinColumns = {@JoinColumn(name = "profile_id")},
			inverseJoinColumns = {@JoinColumn(name = "system_role_id")})
	private List<SystemRole> systemRoles;

	@Enumerated(EnumType.STRING)
	private ProfileStatus status;
}
