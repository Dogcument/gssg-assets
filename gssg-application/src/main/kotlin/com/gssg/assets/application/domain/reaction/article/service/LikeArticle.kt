package com.gssg.assets.application.domain.reaction.article.service

import com.gssg.assets.application.domain.reaction.article.port.`in`.LikeArticleUseCase
import com.gssg.assets.application.domain.reaction.article.port.out.ArticleReactionPersistencePort
import com.gssg.assets.domain.logger
import com.gssg.assets.domain.reaction.article.ArticleReaction
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author Heli
 */
@Service
class LikeArticle(
    private val articleReactionPersistencePort: ArticleReactionPersistencePort
) : LikeArticleUseCase {

    private val logger = logger()

    @Transactional
    override fun command(command: LikeArticleUseCase.Command) {
        val (reactorId, targetId) = command

        val articleReaction = ArticleReaction.like(
            reactorId = reactorId,
            targetId = targetId
        )

        logger.info("게시글 리액션을 데이터베이스에 저장하기 위해 영속성 포트 호출 [reactorId=${reactorId.reactorId}, targetId=${targetId.targetId}")
        articleReactionPersistencePort.insert(articleReaction)
    }
}
