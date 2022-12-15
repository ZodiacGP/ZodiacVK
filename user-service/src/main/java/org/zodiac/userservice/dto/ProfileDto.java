package org.zodiac.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.zodiac.common.validationMarker.AdminGroup;
import org.zodiac.common.validationMarker.UserGroup;
import org.zodiac.common.validationMarker.OnUpdate;
import org.zodiac.userservice.entity.ProfileStatus;
import org.zodiac.userservice.entity.SystemRoleName;
import org.zodiac.userservice.validator.ValidateRoles;

import java.util.List;

@Data
public class ProfileDto {
	@NotBlank(groups = OnUpdate.class)
	private String id;

	@Valid
	@NotNull
	private ProfileDetailDto profileDetail;

	@ValidateRoles(groups = AdminGroup.class, isAdmin = true)
	@ValidateRoles(groups = UserGroup.class, isAdmin = false)
	@NotEmpty
	private List<SystemRoleName> systemRoles;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private ProfileStatus status;
}
