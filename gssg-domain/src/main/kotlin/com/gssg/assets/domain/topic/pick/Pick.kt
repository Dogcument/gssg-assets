package com.gssg.assets.domain.topic.pick

import com.gssg.assets.domain.BaseDomain
import com.gssg.assets.domain.topic.base.TopicId
import java.time.LocalDateTime

/**
 * @Author Heli
 */
class Pick(
    override val id: PickId = PickId(-1L),
    val createdAt: PickCreatedAt = PickCreatedAt(LocalDateTime.MIN),
    val modifiedAt: PickModifiedAt = PickModifiedAt(LocalDateTime.MIN),
    val topicId: TopicId,
    val date: PickDate
) : BaseDomain() {

    companion object {
        fun create(
            newTopicId: TopicId,
            newDate: PickDate
        ) = Pick(
            topicId = newTopicId,
            date = newDate
        )
    }
}
