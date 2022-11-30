package org.zodiac.errorhandler;

public enum ErrorCategory {
	VALIDATION_ERROR,
	AUTHENTICATION_ERROR,
	UNKNOWN_ERROR,
	SERVICE_ERROR,
	ILLEGAL_ARGUMENT_ERROR,
	CONSTRAINT_VIOLATION_ERROR,
	ENTITY_NOT_FOUND_ERROR,
	MISSING_SERVLET_REQUEST_PART_ERROR,
}
