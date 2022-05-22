package com.gssg.assets.domain.reaction

import com.gssg.assets.domain.BaseDomain
import com.gssg.assets.domain.reaction.enums.Status
import java.time.LocalDateTime

/**
 * @Author Heli
 */
class Reaction(
    override val id: ReactionId = ReactionId(-1L),
    val createdAt: ReactionCreatedAt = ReactionCreatedAt(LocalDateTime.MIN),
    val modifiedAt: ReactionModifiedAt = ReactionModifiedAt(LocalDateTime.MIN),
    val targetType: ReactionTargetType,
    val targetId: ReactionTargetId,
    val reactor: ReactionReactor,
    val status: ReactionStatus
) : BaseDomain() {

    companion object {
        fun create(
            targetType: ReactionTargetType,
            targetId: ReactionTargetId,
            reactor: ReactionReactor
        ) = Reaction(
            targetType = targetType,
            targetId = targetId,
            reactor = reactor,
            status = ReactionStatus(status = Status.ACTIVE)
        )
    }

    fun update(
        changedStatus: ReactionStatus?
    ): Reaction {
        val status = changedStatus ?: status
        return Reaction(
            id = id,
            createdAt = createdAt,
            modifiedAt = modifiedAt,
            targetType = targetType,
            targetId = targetId,
            reactor = reactor,
            status = status
        )
    }
}
