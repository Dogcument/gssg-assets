package com.gssg.assets.domain.member.enums

import com.gssg.assets.config.utils.notNull

/**
 * @Author Heli
 */
enum class Role {
    USER, ADMIN;

    companion object {
        private val types = values().associateBy { it.name.lowercase() }
        fun of(type: String) =
            types[type].notNull { "can not parse Member.Role by $type" }
    }
}
