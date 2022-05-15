package com.gssg.assets.persistence.domain.topic.pick.entity

import com.gssg.assets.persistence.common.CommonLongIdEntityTable
import com.gssg.assets.persistence.domain.topic.base.entity.TopicEntities
import org.jetbrains.exposed.sql.javatime.date

/**
 * @Author Heli
 */
object PickEntities : CommonLongIdEntityTable(name = "pick") {
    val topicId = reference("topicId", TopicEntities.id)
    val targetDate = date("targetDate")
}
