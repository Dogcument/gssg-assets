package com.gssg.assets.domain.member.enums

import com.gssg.assets.config.utils.notNull

/**
 * @Author Heli
 */
enum class Status {
    ACTIVE, INACTIVE;

    companion object {
        private val types = values().associateBy { it.name.lowercase() }
        fun of(type: String) = types[type.lowercase()].notNull { "can not parse Member.Status by $type" }
    }
}
