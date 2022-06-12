package com.gssg.assets.domain.friendship.enums

import com.gssg.assets.config.utils.notNull

/**
 * @Author Heli
 */
enum class Status {
    ACTIVE, INACTIVE;

    companion object {
        private val statuses = values().associateBy { it.name.lowercase() }
        fun of(status: String) = statuses[status.lowercase()].notNull {
            "can not parse Friendship.Status by $status"
        }
    }
}
