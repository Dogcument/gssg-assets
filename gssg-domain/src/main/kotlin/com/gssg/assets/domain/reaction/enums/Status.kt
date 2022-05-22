package com.gssg.assets.domain.reaction.enums

import com.gssg.assets.config.utils.notNull

/**
 * @Author Heli
 */
enum class Status {
    ACTIVE, INACTIVE;

    companion object {
        private val types = values().associateBy { it.name.lowercase() }
        fun of(type: String) = types[type].notNull { "can not parse Reaction.Status by $type" }
    }
}
