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
    val reactor: CommentReactionReactor,
    val target: CommentReactionTarget,
    val type: CommentReactionType
) : BaseDomain() {

    companion object {
        fun like(
            reactor: CommentReactionReactor,
            target: CommentReactionTarget
        ) = CommentReaction(
            reactor = reactor,
            target = target,
            type = CommentReactionType(type = ReactionType.LIKE)
        )
    }

    fun unlike() = CommentReaction(
        id = id,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        reactor = reactor,
        target = target,
        type = CommentReactionType(type = ReactionType.NONE)
    )
}
