package com.gssg.assets.persistence.domain.topic.base.entity

import com.gssg.assets.persistence.common.CommonLongIdEntityTable

/**
 * @Author Heli
 */
object TopicEntities : CommonLongIdEntityTable(name = "topic") {
    val text = varchar("text", 32)
    val description = varchar("description", 256)
}
