package com.gssg.gssgbe.common.exception.custom;

import com.gssg.gssgbe.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CustomAuthrizationException extends RuntimeException {

  private final ErrorCode errorCode;

  public CustomAuthrizationException(ErrorCode errorCode) {
    super(errorCode.getReason());
    this.errorCode = errorCode;
  }
}
