package org.zodiac.userservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.zodiac.database.BaseEntity;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class SystemRole extends BaseEntity {

	@Enumerated(EnumType.STRING)
	private SystemRoleName name;
}
