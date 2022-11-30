package org.zodiac.userservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.zodiac.database.BaseEntity;

@Data
@ToString(callSuper = true, exclude = "profile")
@EqualsAndHashCode(callSuper = true, exclude = "profile")
@Entity
public class User extends BaseEntity {
	private String username;

	private String password;

	@OneToOne
	@JoinColumn(name = "profile_id", referencedColumnName = "id")
	private Profile profile;
}
