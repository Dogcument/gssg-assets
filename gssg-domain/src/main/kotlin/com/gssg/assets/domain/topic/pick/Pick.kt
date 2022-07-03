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
    val topic: PickTopic,
    val targetDate: PickTargetDate
) : BaseDomain() {

    companion object {
        fun create(
            existTopic: PickTopic,
            newTargetDate: PickTargetDate
        ) = Pick(
            topic = existTopic,
            targetDate = newTargetDate
        )
    }
}
