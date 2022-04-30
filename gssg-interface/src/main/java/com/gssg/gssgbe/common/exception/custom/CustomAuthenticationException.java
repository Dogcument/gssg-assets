package com.gssg.gssgbe.common.exception.custom;

import com.gssg.gssgbe.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CustomAuthenticationException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomAuthenticationException(final ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }
}
