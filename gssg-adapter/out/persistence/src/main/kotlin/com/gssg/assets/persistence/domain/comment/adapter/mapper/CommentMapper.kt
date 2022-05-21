package com.gssg.assets.persistence.domain.comment.adapter.mapper

import com.gssg.assets.domain.comment.*
import com.gssg.assets.domain.comment.enums.Status
import com.gssg.assets.domain.logger
import com.gssg.assets.persistence.domain.article.adapter.mapper.ArticleMapper
import com.gssg.assets.persistence.domain.comment.entity.CommentEntity
import com.gssg.assets.persistence.domain.comment.repository.CommentRepository
import com.gssg.assets.persistence.domain.member.adapter.mapper.MemberMapper

/**
 * @Author Heli
 */
object CommentMapper {

    private val logger = logger()

    fun toDefinition(comment: Comment): CommentRepository.CommentDefinition {
        logger.info("어댑터 모듈의 매퍼에서 댓글 객체를 엔티티 정의로 변경")
        return CommentRepository.CommentDefinition(
            content = comment.content.content,
            authorId = comment.author.author.longId,
            articleId = comment.article.article.longId,
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
            author = CommentAuthor(author = MemberMapper.toApplication(commentEntity.author)),
            article = CommentArticle(article = ArticleMapper.toApplication(commentEntity.article)),
            status = CommentStatus(status = Status.of(commentEntity.status))
        )
    }
}
