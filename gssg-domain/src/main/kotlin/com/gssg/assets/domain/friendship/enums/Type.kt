package com.gssg.assets.domain.friendship.enums

import com.gssg.assets.config.utils.notNull

/**
 * @Author Heli
 */
enum class Type {
    FOLLOW;

    companion object {
        private val types = values().associateBy { it.name.lowercase() }
        fun of(type: String) = types[type.lowercase()].notNull {
            "can not parse Friendship.Type by $type"
        }
    }
}
