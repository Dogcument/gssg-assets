package com.gssg.assets.application.domain.topic.base.port.out

import com.gssg.assets.domain.topic.base.*
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicLong

/**
 * @Author Heli
 */
internal class MockTopicPersistencePortAdapter : TopicPersistencePort {

    private val db = mutableMapOf<TopicId, Topic>()
    private val distributedId = AtomicLong(1L)


    override fun insert(topic: Topic) {
        val topicId = TopicId(id = distributedId.getAndIncrement())
        db[topicId] = topic
    }

    override fun update(topic: Topic) {
        val topicId = TopicId(id = topic.longId)
        db[topicId] = topic
    }

    override fun findById(topicId: TopicId): Topic? {
        return db[topicId]
    }

    fun initData() {
        val now = LocalDateTime.now()
        (1L..3L).forEach { longId ->
            db[TopicId(id = longId)] = Topic(
                id = TopicId(id = longId),
                createdAt = TopicCreatedAt(createdAt = now),
                modifiedAt = TopicModifiedAt(modifiedAt = now),
                text = TopicText(text = "text $longId"),
                description = TopicDescription(description = "description $longId")
            )
        }
    }

    fun clear() {
        db.clear()
    }
}
