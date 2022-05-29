package com.gssg.assets.application.domain.reaction.article.port.`in`

import com.gssg.assets.domain.reaction.article.ArticleReactionId

/**
 * @Author Heli
 */
interface CancelArticleReactionUseCase {

    fun command(command: Command)

    data class Command(
        val articleReactionId: ArticleReactionId
    )
}
