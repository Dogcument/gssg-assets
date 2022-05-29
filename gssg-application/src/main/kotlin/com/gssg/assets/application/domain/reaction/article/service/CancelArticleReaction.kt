package com.gssg.assets.application.domain.reaction.article.service

import com.gssg.assets.application.domain.reaction.article.port.`in`.CancelArticleReactionUseCase
import com.gssg.assets.application.domain.reaction.article.port.out.ArticleReactionPersistencePort
import com.gssg.assets.application.domain.reaction.article.service.exception.ArticleReactionNotFoundException
import com.gssg.assets.domain.logger
import org.springframework.stereotype.Service

/**
 * @Author Heli
 */
@Service
class CancelArticleReaction(
    private val articleReactionPersistencePort: ArticleReactionPersistencePort
) : CancelArticleReactionUseCase {

    private val logger = logger()

    override fun command(command: CancelArticleReactionUseCase.Command) {
        val articleReactionId = command.articleReactionId

        logger.info("게시글 리액션을 데이베이스에서 조회하기 위해 영속성 포트를 호출")
        val articleReaction = articleReactionPersistencePort.findBy(
            articleReactionId = articleReactionId
        ) ?: throw ArticleReactionNotFoundException(articleReactionId = articleReactionId)

        val canceledArticleReaction = articleReaction.cancel()

        logger.info("게시글 리액션을 데이터베이스에 저장하기 위해 영속성 포트를 호출")
        articleReactionPersistencePort.update(canceledArticleReaction)
    }
}
