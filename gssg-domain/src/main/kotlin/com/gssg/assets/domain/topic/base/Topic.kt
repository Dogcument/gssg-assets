package com.gssg.assets.domain.topic.base

import com.gssg.assets.domain.BaseDomain
import java.time.LocalDateTime

/**
 * @Author Heli
 */
class Topic(
    override val id: TopicId = TopicId(-1L),
    val createdAt: TopicCreatedAt = TopicCreatedAt(LocalDateTime.MIN),
    val modifiedAt: TopicModifiedAt = TopicModifiedAt(LocalDateTime.MIN),
    val text: TopicText,
    val description: TopicDescription
) : BaseDomain() {

    companion object {
        fun create(
            newText: TopicText,
            newDescription: TopicDescription
        ) = Topic(
            text = newText,
            description = newDescription
        )
    }
}
