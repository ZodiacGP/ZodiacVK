package org.zodiac.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.zodiac.common.validationMarker.OnCreate;
import org.zodiac.common.validationMarker.OnUpdate;

@Data
public class UserDto {
	@NotBlank(groups = OnUpdate.class)
	private String id;

	@NotBlank
	@Size(min = 2, max = 32)
	private String username;

	@NotBlank(groups = OnCreate.class)
	@Size(min = 4, max = 550, groups = OnCreate.class)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Valid
	@NotNull
	private ProfileDto profile;
}
