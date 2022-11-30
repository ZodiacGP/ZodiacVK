package org.zodiac.userservice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = RolesValidator.class)
@Repeatable(ValidateRoles.List.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateRoles {
	String message() default "Invalid system roles";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	boolean isAdmin();

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@interface List {
		ValidateRoles[] value();
	}
}
