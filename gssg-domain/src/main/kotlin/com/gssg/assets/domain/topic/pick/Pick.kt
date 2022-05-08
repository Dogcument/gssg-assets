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
    val targetDate: PickTargetDate
) : BaseDomain() {

    companion object {
        fun create(
            newTopicId: TopicId,
            newTargetDate: PickTargetDate
        ) = Pick(
            topicId = newTopicId,
            targetDate = newTargetDate
        )
    }
}
