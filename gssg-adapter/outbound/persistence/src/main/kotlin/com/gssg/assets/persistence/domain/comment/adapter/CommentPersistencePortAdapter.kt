package com.gssg.assets.persistence.domain.comment.adapter

import com.gssg.assets.application.domain.comment.port.out.CommentPersistencePort
import com.gssg.assets.domain.article.ArticleId
import com.gssg.assets.domain.comment.Comment
import com.gssg.assets.persistence.domain.comment.adapter.mapper.CommentMapper
import com.gssg.assets.persistence.domain.comment.repository.CommentRepository

/**
 * @Author Heli
 */
class CommentPersistencePortAdapter(
    private val commentRepository: CommentRepository
) : CommentPersistencePort {

    override fun insert(comment: Comment) {
        val definition = CommentMapper.toDefinition(comment = comment)
        commentRepository.insert(definition = definition)
    }

    override fun update(comment: Comment) {
        val definition = CommentMapper.toDefinition(comment = comment)
        commentRepository.update(id = comment.longId, definition = definition)
    }

    override fun findByArticleId(articleId: ArticleId): List<Comment> {
        val commentEntities = commentRepository.findByArticleId(
            articleId = articleId.id
        )
        return commentEntities.map {
            CommentMapper.toApplication(commentEntity = it)
        }
    }
}
