package org.zodiac.errorhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
public class BaseError {
	private String message;
	private ErrorCategory errorCategory;
}
