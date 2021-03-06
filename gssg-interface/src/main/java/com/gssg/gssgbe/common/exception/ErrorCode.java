package com.gssg.gssgbe.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 암호화
    FAILED_ENCRYPT("E0001", HttpStatus.CONFLICT, "암호화 실패"),
    FAILED_DECRYPT("E0002", HttpStatus.CONFLICT, "복호화 실패"),

    // 인증
    NOT_EXISTS_MEMBER("E1001", HttpStatus.UNAUTHORIZED, "존재하지 않는 회원"),
    NOT_VALID_PASSWORD("E1002", HttpStatus.UNAUTHORIZED, "일치하지 않는 암호"),
    NOT_EXISTS_AUTHORIZATION("E1003", HttpStatus.UNAUTHORIZED, "권한이 존재하지 않는 회원"),
    FAILED_GENERATE_TOKEN("E1010", HttpStatus.UNAUTHORIZED, "토큰 생성 실패"),
    NOT_VALID_TOKEN("E1011", HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰"),

    // 회원
    EXISTS_EMAIL("E2001", HttpStatus.CONFLICT, "이미 존재하는 이메일"),

    // 글감
    NOT_EXISTS_SUBJECT("E3001", HttpStatus.NOT_FOUND, "존재하지 않는 글감"),

    // 기타
    BAD_REQUEST("E0400", HttpStatus.BAD_REQUEST, "잘못된 입력 값"),
    NOT_VALID_REQUEST_BODY("E0400", HttpStatus.BAD_REQUEST, "존재하지 않거나 읽을 수 없는 Request Body"),
    UNAUTHORIZED("E0401", HttpStatus.UNAUTHORIZED, "인증 실패"),
    FORBIDDEN("E0403", HttpStatus.FORBIDDEN, "권한 없음"),
    NOT_FOUND("E0404", HttpStatus.NOT_FOUND, "찾을 수 없음"),
    METHOD_NOT_ALLOWED("E0405", HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메소드"),
    UNKNOWN("E0500", HttpStatus.INTERNAL_SERVER_ERROR, "알수 없는 서버 에러");

    private final String code;
    private final HttpStatus status;
    private final String reason;
}
