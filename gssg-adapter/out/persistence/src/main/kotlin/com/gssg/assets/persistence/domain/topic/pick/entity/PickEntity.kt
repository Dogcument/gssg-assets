package com.gssg.assets.persistence.domain.topic.pick.entity

import com.gssg.assets.persistence.domain.topic.base.entity.TopicEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * @Author Heli
 */
class PickEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PickEntity>(PickEntities)

    val createdAt by PickEntities.createdAt
    val modifiedAt by PickEntities.modifiedAt
    val topic by TopicEntity referencedOn PickEntities.topicId
    val targetDate by PickEntities.targetDate
}
