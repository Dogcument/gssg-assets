package com.gssg.assets.domain.member.enums

import com.gssg.assets.config.utils.notNull

/**
 * @Author Heli
 */
enum class ProfileDogType(
    val displayName: String,
    val highlight: Boolean
) {
    BAEKGU("백구", false),
    JANGGUN("장군", false),
    WUYU("우유", false),
    YORK("요크", false),
    CORGI("코기", false),
    SILVER("실버", false),
    ALEX("알렉스", false);

    companion object {
        private val types = values().associateBy { it.name.lowercase() }
        fun of(type: String) =
            types[type.lowercase()].notNull { "can not parse Member.ProfileDogType by $type" }
    }
}
