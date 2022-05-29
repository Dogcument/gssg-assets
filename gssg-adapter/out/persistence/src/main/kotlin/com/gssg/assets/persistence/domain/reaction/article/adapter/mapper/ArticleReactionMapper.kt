package com.gssg.assets.persistence.domain.reaction.article.adapter.mapper

import com.gssg.assets.domain.logger
import com.gssg.assets.domain.reaction.article.*
import com.gssg.assets.domain.reaction.enums.ReactionType
import com.gssg.assets.persistence.domain.reaction.article.entity.ArticleReactionEntity
import com.gssg.assets.persistence.domain.reaction.article.repository.ArticleReactionRepository

/**
 * @Author Heli
 */
object ArticleReactionMapper {

    private val logger = logger()

    fun toDefinition(articleReaction: ArticleReaction): ArticleReactionRepository.ArticleReactionDefinition {
        logger.info("어댑터 모듈의 매퍼에서 게시글 리액션 객체를 엔티티 정의로 변경")
        return ArticleReactionRepository.ArticleReactionDefinition(
            reactorId = articleReaction.reactorId.reactorId,
            targetId = articleReaction.targetId.targetId,
            type = articleReaction.type.type
        )
    }

    fun toApplication(articleReactionEntity: ArticleReactionEntity): ArticleReaction {
        logger.info("어댑터 모듈의 매퍼에서 데이터베이스 엔티티를 게시글 리액션 객체로 변경")
        return ArticleReaction(
            id = ArticleReactionId(id = articleReactionEntity.id.value),
            createdAt = ArticleReactionCreatedAt(createdAt = articleReactionEntity.createdAt),
            modifiedAt = ArticleReactionModifiedAt(modifiedAt = articleReactionEntity.modifiedAt),
            reactorId = ArticleReactionReactorId(reactorId = articleReactionEntity.reactor.id.value),
            targetId = ArticleReactionTargetId(targetId = articleReactionEntity.target.id.value),
            type = ArticleReactionType(type = ReactionType.of(articleReactionEntity.type))
        )
    }
}
