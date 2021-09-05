package com.gssg.gssgbe.common.exception.custom;

import com.gssg.gssgbe.common.exception.ErrorCode;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private final ErrorCode errorCode;

	public BusinessException(final ErrorCode errorCode) {
		super(errorCode.getReason());
		this.errorCode = errorCode;
	}
}
