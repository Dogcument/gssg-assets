package com.gssg.assets.application.domain.topic.base.port.out

import com.gssg.assets.domain.topic.base.Topic
import com.gssg.assets.domain.topic.base.TopicId

/**
 * @Author Heli
 */
interface TopicPersistencePort {

    fun insert(topic: Topic)

    fun update(topic: Topic)

    fun findById(topicId: TopicId): Topic?
}
