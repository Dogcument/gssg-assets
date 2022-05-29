package com.gssg.assets.application.domain.reaction.article.port.out

import com.gssg.assets.domain.article.ArticleId
import com.gssg.assets.domain.reaction.article.ArticleReaction
import com.gssg.assets.domain.reaction.article.ArticleReactionId

/**
 * @Author Heli
 */
interface ArticleReactionPersistencePort {

    fun insert(articleReaction: ArticleReaction)

    fun update(articleReaction: ArticleReaction)

    fun findBy(articleReactionId: ArticleReactionId): ArticleReaction?

    fun findBy(articleId: ArticleId): List<ArticleReaction>
}
