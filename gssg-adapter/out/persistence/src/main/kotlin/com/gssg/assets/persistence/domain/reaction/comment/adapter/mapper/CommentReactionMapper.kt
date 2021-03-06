package com.gssg.assets.persistence.domain.reaction.comment.adapter.mapper

import com.gssg.assets.domain.logger
import com.gssg.assets.domain.reaction.comment.*
import com.gssg.assets.domain.reaction.enums.ReactionType
import com.gssg.assets.persistence.domain.reaction.comment.entity.CommentReactionEntity
import com.gssg.assets.persistence.domain.reaction.comment.repository.CommentReactionRepository

/**
 * @Author Heli
 */
object CommentReactionMapper {

    private val logger = logger()

    fun toDefinition(commentReaction: CommentReaction): CommentReactionRepository.CommentReactionDefinition {
        logger.info("어댑터 모듈의 매퍼에서 댓글 리액션 객체를 엔티티 정의로 변경")
        return CommentReactionRepository.CommentReactionDefinition(
            reactorId = commentReaction.reactorId.reactorId,
            targetId = commentReaction.targetId.targetId,
            type = commentReaction.type.type
        )
    }

    fun toApplication(commentReactionEntity: CommentReactionEntity): CommentReaction {
        logger.info("어댑터 모듈의 매퍼에서 데이터베이스 엔티티를 댓글 리액션 객체로 변경")
        return CommentReaction(
            id = CommentReactionId(id = commentReactionEntity.id.value),
            createdAt = CommentReactionCreatedAt(createdAt = commentReactionEntity.createdAt),
            modifiedAt = CommentReactionModifiedAt(modifiedAt = commentReactionEntity.modifiedAt),
            reactorId = CommentReactionReactorId(reactorId = commentReactionEntity.reactor.id.value),
            targetId = CommentReactionTargetId(targetId = commentReactionEntity.target.id.value),
            type = CommentReactionType(type = ReactionType.of(commentReactionEntity.type))
        )
    }
}
