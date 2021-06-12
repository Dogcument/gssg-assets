package com.gssg.gssgbe.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  // 암호화
  FAIL_ENCRYPT("E0001", HttpStatus.CONFLICT, "암호화 실패"),
  FAIL_DECRYPT("E0002", HttpStatus.CONFLICT, "복호화 실패"),

  // 인증
  NOT_VALID_PASSWORD("E1001", HttpStatus.NOT_FOUND, "일치하지 않는 암호"),

  // 기타
  BAD_REQUEST("E0400", HttpStatus.BAD_REQUEST, "잘못된 입력 값"),
  UNAUTHORIZED("E0401", HttpStatus.UNAUTHORIZED, "인증 실패"),
  FORBIDDEN("E0403", HttpStatus.FORBIDDEN, "권한 없음"),
  NOT_FOUND("E0404", HttpStatus.NOT_FOUND, "찾을 수 없음"),
  METHOD_NOT_ALLOWED("E0405", HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메소드"),
  UNKNOWN("E0500", HttpStatus.INTERNAL_SERVER_ERROR, "알수 없는 서버 에러");

  private final String code;
  private final HttpStatus status;
  private final String reason;
}
