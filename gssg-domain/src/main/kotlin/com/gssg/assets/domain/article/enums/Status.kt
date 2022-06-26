package com.gssg.assets.domain.article.enums

import com.gssg.assets.config.utils.notNull

/**
 * @Author Heli
 */
enum class Status {
    ACTIVE, INACTIVE, DELETED;

    companion object {
        private val types = values().associateBy { it.name.lowercase() }
        fun of(type: String) = types[type.lowercase()].notNull { "can not parse Article.Status by $type" }
    }
}
