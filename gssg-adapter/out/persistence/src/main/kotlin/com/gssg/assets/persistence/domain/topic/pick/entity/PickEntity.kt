package com.gssg.assets.persistence.domain.topic.pick.entity

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
    val topicId by PickEntities.topicId
    val targetDate by PickEntities.targetDate
}
