package com.gssg.gssgbe.common.exception.advice;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.ErrorResponse;
import com.gssg.gssgbe.common.exception.custom.CustomAuthenticationException;
import com.gssg.gssgbe.common.exception.custom.CustomAuthrizationException;
import java.nio.file.AccessDeniedException;
import javax.security.sasl.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 인증 관련 에러는 세부 정보를 반환하지 않습니다.
 */
@RestControllerAdvice
public class AuthBaseExceptionAdvice extends BaseExceptionAdvice {

    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합니다.
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ErrorResponse handleAuthenticationException(final AuthenticationException ex) {
        preHandle(ex);
        return ErrorResponse.of(ErrorCode.UNAUTHORIZED);
    }

    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합니다.
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorResponse handleAccessDeniedException(final AccessDeniedException ex) {
        preHandle(ex);
        return ErrorResponse.whtioutDetail(ErrorCode.FORBIDDEN);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorResponse handleInsufficientAuthenticationException(
        final InsufficientAuthenticationException ex) {
        preHandle(ex);
        return ErrorResponse.whtioutDetail(ErrorCode.FORBIDDEN);
    }

    @ExceptionHandler(CustomAuthenticationException.class)
    protected ResponseEntity<ErrorResponse> handleCustomAuthenticationException(
        final CustomAuthenticationException ex) {
        preHandle(ex);
        return new ResponseEntity<>(ErrorResponse.whtioutDetail(ex.getErrorCode()),
            ex.getErrorCode().getStatus());
    }

    @ExceptionHandler(CustomAuthrizationException.class)
    protected ResponseEntity<ErrorResponse> handleCustomAuthrizationException(
        final CustomAuthrizationException ex) {
        preHandle(ex);
        return new ResponseEntity<>(ErrorResponse.whtioutDetail(ex.getErrorCode()),
            ex.getErrorCode().getStatus());
    }
}
