package com.gssg.assets.domain.comment.enums

import com.gssg.assets.config.utils.notNull

/**
 * @Author Heli
 */
enum class Status {
    ACTIVE, INACTIVE, DELETED;

    companion object {
        private val types = values().associateBy { it.name.lowercase() }
        fun of(type: String) = types[type].notNull { "can not parse Comment.Status by $type" }
    }
}
