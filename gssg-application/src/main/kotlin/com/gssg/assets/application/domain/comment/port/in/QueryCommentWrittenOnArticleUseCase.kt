package com.gssg.assets.application.domain.comment.port.`in`

import com.gssg.assets.domain.article.ArticleId
import com.gssg.assets.domain.comment.Comment

/**
 * @Author Heli
 */
interface QueryCommentWrittenOnArticleUseCase {

    fun query(query: Query): Result

    data class Query(
        val articleId: ArticleId
    )

    data class Result(
        val comments: List<Comment>
    )
}
