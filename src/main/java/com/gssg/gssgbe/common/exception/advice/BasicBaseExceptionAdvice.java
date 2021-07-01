package com.gssg.gssgbe.common.exception.advice;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.ErrorResponse;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.common.exception.custom.CustomSecurityException;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BasicBaseExceptionAdvice extends BaseExceptionAdvice {

  /**
   * 지원하지 않은 HTTP method 호출 할 경우 발생합니다.
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  protected ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
    preHandle(ex);
    return ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  protected ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
    preHandle(ex);
    return ErrorResponse.of(ErrorCode.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected ErrorResponse handleException(Exception ex) {
    preHandle(ex);
    return ErrorResponse.of(ErrorCode.UNKNOWN);
  }


  /* custom exception */


  @ExceptionHandler(BusinessException.class)
  protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
    preHandle(ex);
    return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), ex.getErrorCode().getStatus());
  }

  @ExceptionHandler(CustomSecurityException.class)
  protected ResponseEntity<ErrorResponse> handleCustomSecurityException(CustomSecurityException ex) {
    preHandle(ex);
    return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), ex.getErrorCode().getStatus());
  }
}
