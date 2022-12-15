package org.zodiac.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.zodiac.common.validationMarker.OnUpdate;


@Data
public class ProfileDetailDto {
	@NotBlank(groups = OnUpdate.class)
	private String id;

	@NotBlank
	@Size(min = 2, max = 32)
	private String firstName;

	@NotBlank
	@Size(min = 2, max = 32)
	private String lastName;

	@Email
	@Size(min = 6, max = 32)
	private String email;
}
