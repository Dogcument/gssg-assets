package com.gssg.gssgbe.common.exception.custom;

import com.gssg.gssgbe.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CustomSecurityException extends RuntimeException {

  private final ErrorCode errorCode;

  public CustomSecurityException(ErrorCode errorCode) {
    super(errorCode.getReason());
    this.errorCode = errorCode;
  }
}
