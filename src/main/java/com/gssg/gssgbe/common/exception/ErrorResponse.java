package com.gssg.gssgbe.common.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.slf4j.MDC;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gssg.gssgbe.common.exception.custom.BusinessException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

	private final String code;
	private final String codeName;
	private final String message;
	private final LocalDateTime time = LocalDateTime.now();
	private final String transactionId = MDC.get("transactionId");
	private final List<FieldError> errors;

	public static ErrorResponse of(final ErrorCode errorCode, final BindingResult bindingResult) {
		return ErrorResponse.builder()
			.code(errorCode.getCode())
			.codeName(errorCode.name())
			.message(errorCode.getReason())
			.errors(FieldError.of(bindingResult))
			.build();
	}

	public static ErrorResponse of(final ErrorCode errorCode, final List<FieldError> errors) {
		return ErrorResponse.builder()
			.code(errorCode.getCode())
			.codeName(errorCode.name())
			.message(errorCode.getReason())
			.errors(errors)
			.build();
	}

	public static ErrorResponse of(final ErrorCode errorCode) {
		return ErrorResponse.builder()
			.code(errorCode.getCode())
			.codeName(errorCode.name())
			.message(errorCode.getReason())
			.errors(new ArrayList<>())
			.build();
	}

	public static ErrorResponse whtioutDetail(final ErrorCode errorCode) {
		return ErrorResponse.builder()
			.code(errorCode.getCode())
			.build();
	}

	public static ErrorResponse of(final MethodArgumentTypeMismatchException ex) {
		final String value = (ex.getValue() == null) ? "" : ex.getValue().toString();
		final List<FieldError> errors = Collections.singletonList(FieldError.of(ex.getName(), value, ex.getErrorCode()));
		return ErrorResponse.of(ErrorCode.BAD_REQUEST, errors);
	}

	public static ErrorResponse of(final MissingServletRequestParameterException ex) {
		final List<FieldError> errors = Collections.singletonList(FieldError.of(ex.getParameterName(), null, "Not exist"));
		return ErrorResponse.of(ErrorCode.BAD_REQUEST, errors);
	}

	public static ErrorResponse of(final ConstraintViolationException ex) {
		final List<FieldError> errors = ex.getConstraintViolations().stream()
			.map(violation -> FieldError.of(extractPropertyName(violation.getPropertyPath()), null,
				violation.getMessage()))
			.collect(Collectors.toList());
		return ErrorResponse.of(ErrorCode.BAD_REQUEST, errors);
	}

	public static ErrorResponse of(final BusinessException ex) {
		return ErrorResponse.of(ex.getErrorCode());
	}

	/**
	 * 속성 전체 경로에서 속성 이름만 가져옵니다.
	 *
	 * @param propertyPath 속성 전체 경로
	 * @return
	 */
	private static String extractPropertyName(final Path propertyPath) {
		final String pathString = propertyPath.toString();
		return pathString.substring(pathString.lastIndexOf('.') + 1);
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class FieldError {

		private final String field;
		private final String value;
		private final String reason;

		public static FieldError of(final String field, final String value, final String reason) {
			return new FieldError(field, value, reason);
		}

		public static FieldError of(final org.springframework.validation.FieldError fieldError) {
			return new FieldError(
				fieldError.getField(),
				(fieldError.getRejectedValue() == null) ? "" : fieldError.getRejectedValue().toString(),
				fieldError.getDefaultMessage());
		}

		private static List<FieldError> of(final BindingResult bindingResult) {
			return bindingResult.getFieldErrors().stream()
				.map(FieldError::of)
				.collect(Collectors.toList());
		}
	}
}
