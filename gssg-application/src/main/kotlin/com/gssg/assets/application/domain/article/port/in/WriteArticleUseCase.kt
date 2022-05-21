package com.gssg.assets.application.domain.article.port.`in`

import com.gssg.assets.domain.article.Article

/**
 * @Author Heli
 */
interface WriteArticleUseCase {

    fun command(command: Command)

    data class Command(
        val article: Article
    )
}
