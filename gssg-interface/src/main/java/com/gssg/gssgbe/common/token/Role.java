package com.gssg.gssgbe.common.token;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum Role {
    ADMIN("ROLE_ADMIN", "관리자 권한"),
    MEMBER("ROLE_MEMBER", "회원 권한"),
    UNKNOWN("UNKNOWN", "알 수 없는 권한");

    private static final Map<String, Role> roleMap = Collections.unmodifiableMap(
        Stream.of(values()).collect(Collectors.toMap(Role::getCode, Function.identity())));
    private final String code;
    private final String description;

    public static Role of(final String code) {
        return Optional.ofNullable(roleMap.get(code)).orElse(UNKNOWN);
    }
}
