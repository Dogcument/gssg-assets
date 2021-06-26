package com.gssg.gssgbe.common.exception.advice;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.ErrorResponse;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class BadRequestExceptionAdvice extends ExceptionAdvice {

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

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    return ErrorResponse.of(ErrorCode.NOT_VALID_REQUEST_BODY, createLogId(ex));
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
}
