package com.gssg.gssgbe.common.exception;

import com.gssg.gssgbe.common.exception.custom.BusinessException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

  private final String code;
  private final String codeName;
  private final String message;
  private final LocalDateTime time;
  private final UUID logId;
  private final List<FieldError> errors;

  public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult, UUID logId) {
    return ErrorResponse.builder()
        .code(errorCode.getCode())
        .codeName(errorCode.name())
        .message(errorCode.getReason())
        .time(LocalDateTime.now())
        .errors(FieldError.of(bindingResult))
        .logId(logId)
        .build();
  }

  public static ErrorResponse of(ErrorCode errorCode, List<FieldError> errors, UUID logId) {
    return ErrorResponse.builder()
        .code(errorCode.getCode())
        .codeName(errorCode.name())
        .message(errorCode.getReason())
        .time(LocalDateTime.now())
        .errors(errors)
        .logId(logId)
        .build();
  }

  public static ErrorResponse of(ErrorCode errorCode, UUID logId) {
    return ErrorResponse.builder()
        .code(errorCode.getCode())
        .codeName(errorCode.name())
        .message(errorCode.getReason())
        .time(LocalDateTime.now())
        .errors(new ArrayList<>())
        .logId(logId)
        .build();
  }

  public static ErrorResponse of(MethodArgumentTypeMismatchException ex, UUID logId) {
    String value = (ex.getValue() == null) ? "" : ex.getValue().toString();
    List<FieldError> errors = Collections.singletonList(FieldError.of(ex.getName(), value, ex.getErrorCode()));
    return ErrorResponse.of(ErrorCode.BAD_REQUEST, errors, logId);
  }

  public static ErrorResponse of(MissingServletRequestParameterException ex, UUID logId) {
    List<FieldError> errors = Collections.singletonList(FieldError.of(ex.getParameterName(), null, "Not exist"));
    return ErrorResponse.of(ErrorCode.BAD_REQUEST, errors, logId);
  }

  public static ErrorResponse of(ConstraintViolationException ex, UUID logId) {
    List<FieldError> errors = ex.getConstraintViolations().stream()
        .map(violation -> FieldError.of(extractPropertyName(violation.getPropertyPath()), null, violation.getMessage()))
        .collect(Collectors.toList());
    return ErrorResponse.of(ErrorCode.BAD_REQUEST, errors, logId);
  }

  public static ErrorResponse of(BusinessException ex, UUID logId) {
    return ErrorResponse.of(ex.getErrorCode(), logId);
  }

  /**
   * 속성 전체 경로에서 속성 이름만 가져옵니다.
   *
   * @param propertyPath 속성 전체 경로
   * @return
   */
  private static String extractPropertyName(Path propertyPath) {
    String pathString = propertyPath.toString();
    return pathString.substring(pathString.lastIndexOf('.') + 1);
  }

  @Getter
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class FieldError {

    private final String field;
    private final String value;
    private final String reason;

    public static FieldError of(String field, String value, String reason) {
      return new FieldError(field, value, reason);
    }

    public static FieldError of(org.springframework.validation.FieldError fieldError) {
      return new FieldError(
          fieldError.getField(),
          (fieldError.getRejectedValue() == null) ? "" : fieldError.getRejectedValue().toString(),
          fieldError.getDefaultMessage());
    }

    private static List<FieldError> of(BindingResult bindingResult) {
      return bindingResult.getFieldErrors().stream()
          .map(FieldError::of)
          .collect(Collectors.toList());
    }
  }
}
