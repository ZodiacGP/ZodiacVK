package org.zodiac.userservice.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.zodiac.userservice.entity.SystemRoleName;

import java.util.Set;

public class RolesValidator implements ConstraintValidator<ValidateRoles, Set<SystemRoleName>> {
	private boolean isAdmin;

	@Override
	public void initialize(ValidateRoles constraintAnnotation) {
		this.isAdmin = constraintAnnotation.isAdmin();
	}

	@Override
	public boolean isValid(Set<SystemRoleName> roles, ConstraintValidatorContext context) {
		return isAdmin
				? roles.stream().allMatch(role -> role == SystemRoleName.ADMIN)
				: roles.stream().noneMatch(role -> role == SystemRoleName.ADMIN);
	}
}
