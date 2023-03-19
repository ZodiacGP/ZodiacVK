package org.zodiac.databaseerrorhandler;

import jakarta.persistence.EntityNotFoundException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zodiac.errorhandler.BaseError;
import org.zodiac.errorhandler.ErrorCategory;

@RestControllerAdvice
public class DBErrorHandler {
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler
	public BaseError handleEntityNotFoundException(EntityNotFoundException exception) {
		return new BaseError(exception.getMessage(), ErrorCategory.ENTITY_NOT_FOUND_ERROR);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public BaseError handleIllegalArgumentException(PSQLException exception) {
		return new BaseError(exception.getMessage(), ErrorCategory.CONSTRAINT_VIOLATION_ERROR);
	}
}
