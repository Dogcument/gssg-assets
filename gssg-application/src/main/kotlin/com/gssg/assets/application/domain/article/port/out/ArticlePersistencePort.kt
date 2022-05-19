package com.gssg.assets.application.domain.article.port.out

import com.gssg.assets.domain.article.Article
import com.gssg.assets.domain.article.ArticleId

/**
 * @Author Heli
 */
interface ArticlePersistencePort {

    fun insert(article: Article)

    fun update(article: Article)

    fun findById(articleId: ArticleId): Article?
}
