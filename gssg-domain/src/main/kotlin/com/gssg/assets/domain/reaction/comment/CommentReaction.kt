package com.gssg.assets.domain.reaction.comment

import com.gssg.assets.domain.BaseDomain
import com.gssg.assets.domain.reaction.enums.ReactionType
import java.time.LocalDateTime

/**
 * @Author Heli
 */
class CommentReaction(
    override val id: CommentReactionId = CommentReactionId(-1L),
    val createdAt: CommentReactionCreatedAt = CommentReactionCreatedAt(LocalDateTime.MIN),
    val modifiedAt: CommentReactionModifiedAt = CommentReactionModifiedAt(LocalDateTime.MIN),
    val reactorId: CommentReactionReactorId,
    val targetId: CommentReactionTargetId,
    val type: CommentReactionType
) : BaseDomain() {

    companion object {
        fun like(
            reactorId: CommentReactionReactorId,
            targetId: CommentReactionTargetId
        ) = CommentReaction(
            reactorId = reactorId,
            targetId = targetId,
            type = CommentReactionType(type = ReactionType.LIKE)
        )
    }

    fun cancel() = CommentReaction(
        id = id,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        reactorId = reactorId,
        targetId = targetId,
        type = CommentReactionType(type = ReactionType.NONE)
    )
}
