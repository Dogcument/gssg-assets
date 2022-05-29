package com.gssg.assets.domain.reaction.article

import com.gssg.assets.domain.BaseDomain
import com.gssg.assets.domain.reaction.enums.ReactionType
import java.time.LocalDateTime

/**
 * @Author Heli
 */
class ArticleReaction(
    override val id: ArticleReactionId = ArticleReactionId(-1L),
    val createdAt: ArticleReactionCreatedAt = ArticleReactionCreatedAt(LocalDateTime.MIN),
    val modifiedAt: ArticleReactionModifiedAt = ArticleReactionModifiedAt(LocalDateTime.MIN),
    val reactorId: ArticleReactionReactorId,
    val targetId: ArticleReactionTargetId,
    val type: ArticleReactionType
) : BaseDomain() {

    companion object {
        fun like(
            reactorId: ArticleReactionReactorId,
            targetId: ArticleReactionTargetId
        ) = ArticleReaction(
            reactorId = reactorId,
            targetId = targetId,
            type = ArticleReactionType(type = ReactionType.LIKE)
        )
    }

    fun cancel() = ArticleReaction(
        id = id,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        reactorId = reactorId,
        targetId = targetId,
        type = ArticleReactionType(type = ReactionType.NONE)
    )
}
