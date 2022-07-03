package com.gssg.assets.persistence.domain.topic.base.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * @Author Heli
 */
class TopicEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TopicEntity>(TopicEntities)

    val createdAt by TopicEntities.createdAt
    val modifiedAt by TopicEntities.modifiedAt
    val text by TopicEntities.text
    val description by TopicEntities.description
}
