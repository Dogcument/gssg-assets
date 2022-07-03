package com.gssg.assets.domain.topic.pick

import com.gssg.assets.domain.BaseDomain
import java.time.LocalDateTime

/**
 * @Author Heli
 */
class Pick(
    override val id: PickId = PickId(-1L),
    val createdAt: PickCreatedAt = PickCreatedAt(LocalDateTime.MIN),
    val modifiedAt: PickModifiedAt = PickModifiedAt(LocalDateTime.MIN),
    val topicId: PickTopicId,
    val targetDate: PickTargetDate
) : BaseDomain() {

    companion object {
        fun create(
            existTopicId: PickTopicId,
            newTargetDate: PickTargetDate
        ) = Pick(
            topicId = existTopicId,
            targetDate = newTargetDate
        )
    }
}
