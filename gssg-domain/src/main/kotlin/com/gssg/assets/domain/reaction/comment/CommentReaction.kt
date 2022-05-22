package com.gssg.assets.domain.reaction.comment

import com.gssg.assets.domain.BaseDomain
import com.gssg.assets.domain.reaction.enums.Status
import java.time.LocalDateTime

/**
 * @Author Heli
 */
class CommentReaction(
    override val id: CommentReactionId = CommentReactionId(-1L),
    val createdAt: CommentReactionCreatedAt = CommentReactionCreatedAt(LocalDateTime.MIN),
    val modifiedAt: CommentReactionModifiedAt = CommentReactionModifiedAt(LocalDateTime.MIN),
    val reactor: CommentReactionReactor,
    val target: CommentReactionTarget,
    val status: CommentReactionStatus
) : BaseDomain() {

    companion object {
        fun create(
            reactor: CommentReactionReactor,
            target: CommentReactionTarget
        ) = CommentReaction(
            reactor = reactor,
            target = target,
            status = CommentReactionStatus(status = Status.ACTIVE)
        )
    }

    fun update(
        changedStatus: CommentReactionStatus?
    ): CommentReaction {
        val status = changedStatus ?: status
        return CommentReaction(
            id = id,
            createdAt = createdAt,
            modifiedAt = modifiedAt,
            reactor = reactor,
            target = target,
            status = status
        )
    }
}
