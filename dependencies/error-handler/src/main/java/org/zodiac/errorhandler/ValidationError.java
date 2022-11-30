package org.zodiac.errorhandler;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ValidationError extends BaseError {
	private List<FieldError> fieldErrors;
}
