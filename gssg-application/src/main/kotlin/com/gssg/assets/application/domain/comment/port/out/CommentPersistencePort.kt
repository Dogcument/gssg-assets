package com.gssg.assets.application.domain.comment.port.out

import com.gssg.assets.domain.article.ArticleId
import com.gssg.assets.domain.comment.Comment

/**
 * @Author Heli
 */
interface CommentPersistencePort {

    fun insert(comment: Comment)

    fun update(comment: Comment)

    fun findByArticleId(articleId: ArticleId): List<Comment>
}
