package com.gssg.assets.application.domain.reaction.article.port.out

import com.gssg.assets.domain.article.ArticleId
import com.gssg.assets.domain.reaction.article.ArticleReaction

/**
 * @Author Heli
 */
interface ArticleReactionPersistencePort {

    fun insert(articleReaction: ArticleReaction)

    fun update(articleReaction: ArticleReaction)

    fun findBy(articleId: ArticleId): List<ArticleReaction>
}
