package com.gssg.assets.persistence.domain.comment.adapter.mapper

import com.gssg.assets.domain.comment.*
import com.gssg.assets.domain.comment.enums.Status
import com.gssg.assets.domain.logger
import com.gssg.assets.persistence.domain.comment.entity.CommentEntity
import com.gssg.assets.persistence.domain.comment.repository.CommentRepository

/**
 * @Author Heli
 */
object CommentMapper {

    private val logger = logger()

    fun toDefinition(comment: Comment): CommentRepository.CommentDefinition {
        logger.info("어댑터 모듈의 매퍼에서 댓글 객체를 엔티티 정의로 변경")
        return CommentRepository.CommentDefinition(
            content = comment.content.content,
            authorId = comment.authorId.id,
            articleId = comment.articleId.id,
            status = comment.status.status
        )
    }

    fun toApplication(commentEntity: CommentEntity): Comment {
        logger.info("어댑터 모듈의 매퍼에서 데이터베이스 엔티티를 댓글 객체로 변경")
        return Comment(
            id = CommentId(id = commentEntity.id.value),
            createdAt = CommentCreatedAt(createdAt = commentEntity.createdAt),
            modifiedAt = CommentModifiedAt(modifiedAt = commentEntity.modifiedAt),
            content = CommentContent(content = commentEntity.content),
            authorId = CommentAuthorId(id = commentEntity.author.id.value),
            articleId = CommentArticleId(id = commentEntity.article.id.value),
            status = CommentStatus(status = Status.of(commentEntity.status))
        )
    }
}
