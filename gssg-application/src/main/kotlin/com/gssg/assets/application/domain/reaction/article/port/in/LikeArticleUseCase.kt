package com.gssg.assets.application.domain.reaction.article.port.`in`

import com.gssg.assets.domain.reaction.article.ArticleReactionReactorId
import com.gssg.assets.domain.reaction.article.ArticleReactionTargetId

/**
 * @Author Heli
 */
interface LikeArticleUseCase {

    fun command(command: Command)

    data class Command(
        val reactorId: ArticleReactionReactorId,
        val targetId: ArticleReactionTargetId
    )
}
