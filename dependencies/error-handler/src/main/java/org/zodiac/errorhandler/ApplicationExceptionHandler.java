package org.zodiac.errorhandler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	/*@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler
	public BaseError handleEntityNotFoundException(EntityNotFoundException exception) {
		return new BaseError(exception.getMessage(), ErrorCategory.ENTITY_NOT_FOUND_ERROR);
	}*/

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public BaseError handleIllegalArgumentException(IllegalArgumentException exception) {
		return new BaseError(exception.getMessage(), ErrorCategory.ILLEGAL_ARGUMENT_ERROR);
	}

	/*@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public BaseError handleIllegalArgumentException(PSQLException exception) {
		return new BaseError(exception.getMessage(), ErrorCategory.CONSTRAINT_VIOLATION_ERROR);
	}*/

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public ValidationError handleConstraintViolationException(ConstraintViolationException exception) {
		List<FieldError> errorMessages = exception.getConstraintViolations()
				.stream()
				.map(violation -> new FieldError(exception.getClass().getSimpleName(),
						violation.getPropertyPath().iterator().next().getName(),
						violation.getMessage())
				)
				.toList();

		return ValidationError.builder()
				.errorCategory(ErrorCategory.CONSTRAINT_VIOLATION_ERROR)
				.message(exception.getMessage())
				.fieldErrors(errorMessages)
				.build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public ValidationError handleBindException(BindException exception) {
		List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
		List<FieldError> fieldErrors = allErrors.stream()
				.map(objectError -> {
					if (objectError instanceof org.springframework.validation.FieldError fieldError) {
						return new FieldError(fieldError.getObjectName(),
								fieldError.getField(),
								fieldError.getDefaultMessage());
					}
					return new FieldError(objectError.getObjectName(),
							"",
							objectError.getDefaultMessage());
				}).toList();

		return ValidationError.builder()
				.errorCategory(ErrorCategory.VALIDATION_ERROR)
				.message(exception.getMessage())
				.fieldErrors(fieldErrors)
				.build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public BaseError handleUnsupportedOperationException(UnsupportedOperationException exception) {
		return new BaseError(exception.getMessage(), ErrorCategory.SERVICE_ERROR);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public BaseError handleMissingServletRequestPartException(MissingServletRequestPartException exception) {
		return new BaseError(exception.getMessage(), ErrorCategory.MISSING_SERVLET_REQUEST_PART_ERROR);
	}
}
