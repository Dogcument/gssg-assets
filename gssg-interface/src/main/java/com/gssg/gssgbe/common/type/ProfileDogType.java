package com.gssg.gssgbe.common.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProfileDogType {

    BAEKGU("백구", false),
    JANGGUN("장군", false),
    WUYU("우유", false),
    YORK("요크", false),
    CORGI("코기", false),
    SILVER("실버", false),
    ALEX("알렉스", false),
    ;

    private final String name;
    private final Boolean highlight;

    public static ProfileDogType getDefault() {
        return ProfileDogType.BAEKGU;
    }
}
