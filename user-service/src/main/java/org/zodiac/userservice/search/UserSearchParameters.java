package org.zodiac.userservice.search;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.zodiac.userservice.entity.ProfileStatus;
import org.zodiac.userservice.entity.User;
import org.zodiac.userservice.search.specificationBuilder.UserSpecificationBuilder;

@Data
public class UserSearchParameters {
	private String firstName;
	private String lastName;
	private ProfileStatus status = ProfileStatus.ACTIVE;

	public Specification<User> toSpecification() {
		return new UserSpecificationBuilder()
				.hasFirstName(firstName)
				.hasLastName(lastName)
				.hasStatus(status)
				.build();
	}
}