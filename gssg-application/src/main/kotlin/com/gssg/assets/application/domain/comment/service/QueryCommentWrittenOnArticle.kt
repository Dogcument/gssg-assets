package com.gssg.assets.application.domain.comment.service

import com.gssg.assets.application.domain.comment.port.`in`.QueryCommentWrittenOnArticleUseCase
import com.gssg.assets.application.domain.comment.port.out.CommentPersistencePort
import com.gssg.assets.domain.logger
import org.springframework.stereotype.Service

/**
 * @Author Heli
 */
@Service
class QueryCommentWrittenOnArticle(
    private val commentPersistencePort: CommentPersistencePort
) : QueryCommentWrittenOnArticleUseCase {

    private val logger = logger()

    override fun query(query: QueryCommentWrittenOnArticleUseCase.Query): QueryCommentWrittenOnArticleUseCase.Result {
        val articleId = query.articleId

        logger.info("댓글을 데이터베이스에서 조회하기 위해 영속성 포트를 호출")
        val comments = commentPersistencePort.findByArticleId(articleId = articleId)

        return QueryCommentWrittenOnArticleUseCase.Result(
            comments = comments
        )
    }
}
