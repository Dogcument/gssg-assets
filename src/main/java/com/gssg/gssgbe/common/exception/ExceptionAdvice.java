package com.gssg.gssgbe.common.exception;

import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.common.exception.custom.CustomAuthenticationException;
import com.gssg.gssgbe.common.exception.custom.CustomAuthrizationException;
import com.gssg.gssgbe.common.exception.custom.CustomSecurityException;
import java.nio.file.AccessDeniedException;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import javax.security.sasl.AuthenticationException;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

  private UUID createLogId(Exception ex) {
    UUID uuid = UUID.randomUUID();
    log.error("### {}, {}", uuid, ex.getClass().getSimpleName(), ex);
    return uuid;
  }

  /**
   * 지원하지 않은 HTTP method 호출 할 경우 발생합니다.
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  protected ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
    return ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED, createLogId(ex));
  }

  /**
   * ModelAttribute 에 binding error 발생시 BindException 발생한다.
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ErrorResponse handleBindException(BindException ex) {
    return ErrorResponse.of(ErrorCode.BAD_REQUEST, ex.getBindingResult(), createLogId(ex));
  }

  /**
   * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합니다.
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  protected ErrorResponse handleAuthenticationException(AuthenticationException ex) {
    return ErrorResponse.of(ErrorCode.UNAUTHORIZED, createLogId(ex));
  }

  /**
   * 필요한 param 값이 들어오지 않았을 때 발생합니다.
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
    return ErrorResponse.of(ex, createLogId(ex));
  }

  /**
   * type 이 일치하지 않아 binding 못할 경우 발생합니다.
   * 주로 @RequestParam enum 으로 binding 못했을 경우 발생합니다.
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
    return ErrorResponse.of(ex, createLogId(ex));
  }

  /**
   * javax.validation 을 통과하지 못하면 에러가 발생합니다.
   * 주로 @NotBlank, @NotEmpty, @NotNull 에서 발생합니다.
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ErrorResponse handleConstraintViolationException(ConstraintViolationException ex) {
    return ErrorResponse.of(ex, createLogId(ex));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
    return ErrorResponse.of(ErrorCode.BAD_REQUEST, createLogId(ex));
  }

  /**
   * Valid or Validated 으로 binding error 발생시 발생합니다.
   * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 이 실패하는 경우 발생합니다.
   * 주로 @RequestBody, @RequestPart 어노테이션에서 발생합니다.
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    return ErrorResponse.of(ErrorCode.BAD_REQUEST, ex.getBindingResult(), createLogId(ex));
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  protected ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
    return ErrorResponse.of(ErrorCode.NOT_FOUND, createLogId(ex));
  }

  /**
   * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합니다.
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  protected ErrorResponse handleAccessDeniedException(AccessDeniedException ex) {
    return ErrorResponse.of(ErrorCode.FORBIDDEN, createLogId(ex));
  }

  @ExceptionHandler(InsufficientAuthenticationException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  protected ErrorResponse handleInsufficientAuthenticationException(InsufficientAuthenticationException ex) {
    return ErrorResponse.of(ErrorCode.FORBIDDEN, createLogId(ex));
  }


  /* custom exception */


  @ExceptionHandler(BusinessException.class)
  protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
    return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode(), createLogId(ex)), ex.getErrorCode().getStatus());
  }

  @ExceptionHandler(CustomSecurityException.class)
  protected ResponseEntity<ErrorResponse> handleCustomSecurityException(CustomSecurityException ex) {
    return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode(), createLogId(ex)), ex.getErrorCode().getStatus());
  }

  /**
   * 인증 관련 에러는 세부 정보를 반환하지 않습니다.
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(CustomAuthenticationException.class)
  protected ResponseEntity<ErrorResponse> handleCustomAuthenticationException(CustomAuthenticationException ex) {
    return new ResponseEntity<>(ErrorResponse.whtioutDetail(ex.getErrorCode(), createLogId(ex)), ex.getErrorCode().getStatus());
  }

  @ExceptionHandler(CustomAuthrizationException.class)
  protected ResponseEntity<ErrorResponse> handleCustomAuthrizationException(CustomAuthrizationException ex) {
    return new ResponseEntity<>(ErrorResponse.whtioutDetail(ex.getErrorCode(), createLogId(ex)), ex.getErrorCode().getStatus());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected ErrorResponse handleException(Exception ex) {
    return ErrorResponse.of(ErrorCode.UNKNOWN, createLogId(ex));
  }
}
