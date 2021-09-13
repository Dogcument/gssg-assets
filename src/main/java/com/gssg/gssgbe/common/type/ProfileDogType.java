package com.gssg.gssgbe.common.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProfileDogType {

	BAEKGU("백구"),
	JANGGUN("장군"),
	WUYU("우유"),
	YORK("요크"),
	CORGI("코기"),
	SILVER("실버"),
	ALEX("알렉스"),
	;

	private final String name;

	public static ProfileDogType getDefault() {
		return ProfileDogType.BAEKGU;
	}
}
