package com.gssg.assets.persistence.domain.topic.base.adapter

import com.gssg.assets.application.domain.topic.base.port.out.TopicPersistencePort
import com.gssg.assets.domain.topic.base.Topic
import com.gssg.assets.domain.topic.base.TopicId
import com.gssg.assets.persistence.domain.topic.base.adapter.mapper.TopicMapper
import com.gssg.assets.persistence.domain.topic.base.repository.TopicRepository

/**
 * @Author Heli
 */
class TopicPersistencePortAdapter(
    private val topicRepository: TopicRepository
) : TopicPersistencePort {

    override fun insert(topic: Topic) {
        val topicDefinition = TopicMapper.toDefinition(topic)
        topicRepository.insert(topicDefinition = topicDefinition)
    }

    override fun update(topic: Topic) {
        val topicDefinition = TopicMapper.toDefinition(topic)
        topicRepository.update(id = topic.longId, topicDefinition = topicDefinition)
    }

    override fun findById(topicId: TopicId): Topic? {
        val topicEntity = topicRepository.findById(id = topicId.id) ?: return null
        return TopicMapper.toApplication(topicEntity = topicEntity)
    }
}
