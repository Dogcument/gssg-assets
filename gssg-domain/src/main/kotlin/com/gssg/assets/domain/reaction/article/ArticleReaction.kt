package com.gssg.assets.domain.reaction.article

import com.gssg.assets.domain.BaseDomain
import com.gssg.assets.domain.reaction.enums.Status
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
    val status: ArticleReactionStatus
) : BaseDomain() {

    companion object {
        fun create(
            reactor: ArticleReactionReactor,
            target: ArticleReactionTarget
        ) = ArticleReaction(
            reactor = reactor,
            target = target,
            status = ArticleReactionStatus(status = Status.ACTIVE)
        )
    }

    fun update(
        changedStatus: ArticleReactionStatus?
    ): ArticleReaction {
        val status = changedStatus ?: status
        return ArticleReaction(
            id = id,
            createdAt = createdAt,
            modifiedAt = modifiedAt,
            reactor = reactor,
            target = target,
            status = status
        )
    }
}
