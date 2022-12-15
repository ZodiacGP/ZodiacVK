package org.zodiac.userservice.search.specificationBuilder;


import org.springframework.data.jpa.domain.Specification;
import org.zodiac.common.search.AbstractSpecificationBuilder;
import org.zodiac.userservice.entity.*;


public class UserSpecificationBuilder extends AbstractSpecificationBuilder<User> {
	public UserSpecificationBuilder hasFirstName(String firstName) {
		Specification<User> specification = (root, query, criteriaBuilder) ->
				criteriaBuilder.like(criteriaBuilder.lower(root.join(User_.profile)
						.join(Profile_.profileDetail)
						.get(ProfileDetail_.LAST_NAME)), firstName.toLowerCase() + "%");

		addIfNotNull(firstName, specification);

		return this;
	}

	public UserSpecificationBuilder hasLastName(String lastName) {
		Specification<User> specification = (root, query, criteriaBuilder) ->
				criteriaBuilder.like(criteriaBuilder.lower(root.join(User_.profile)
						.join(Profile_.profileDetail)
						.get(ProfileDetail_.LAST_NAME)), lastName.toLowerCase() + "%");

		addIfNotNull(lastName, specification);

		return this;
	}

	public UserSpecificationBuilder hasStatus(ProfileStatus status) {
		Specification<User> specification = (root, query, criteriaBuilder) ->
				criteriaBuilder.equal(root.get(User_.PROFILE).get(Profile_.STATUS), status);

		addIfNotNull(status, specification);

		return this;
	}
}