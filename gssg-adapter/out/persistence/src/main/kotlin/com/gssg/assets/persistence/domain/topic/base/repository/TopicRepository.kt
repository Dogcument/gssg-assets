package com.gssg.assets.persistence.domain.topic.base.repository

import com.gssg.assets.persistence.common.CommonDefinition
import com.gssg.assets.persistence.domain.topic.base.entity.TopicEntity

/**
 * @Author Heli
 */
interface TopicRepository {
    fun insert(topicDefinition: TopicDefinition)

    fun update(id: Long, topicDefinition: TopicDefinition)

    fun findById(id: Long): TopicEntity?

    data class TopicDefinition(
        val text: String,
        val description: String
    ) : CommonDefinition
}
