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
    val reactor: ArticleReactionReactor,
    val target: ArticleReactionTarget,
    val type: ArticleReactionType
) : BaseDomain() {

    companion object {
        fun like(
            reactor: ArticleReactionReactor,
            target: ArticleReactionTarget
        ) = ArticleReaction(
            reactor = reactor,
            target = target,
            type = ArticleReactionType(type = ReactionType.LIKE)
        )
    }

    fun unlike() = ArticleReaction(
        id = id,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        reactor = reactor,
        target = target,
        type = ArticleReactionType(type = ReactionType.NONE)
    )
}
