package com.gssg.assets.persistence.domain.comment.repository

import com.gssg.assets.domain.comment.enums.Status
import com.gssg.assets.persistence.common.CommonDefinition
import com.gssg.assets.persistence.domain.comment.entity.CommentEntity

/**
 * @Author Heli
 */
interface CommentRepository {

    fun insert(definition: CommentDefinition)

    fun update(id: Long, definition: CommentDefinition)

    fun findById(commentId: Long): CommentEntity?

    fun findByArticleId(articleId: Long): List<CommentEntity>

    data class CommentDefinition(
        val content: String,
        val authorId: Long,
        val articleId: Long,
        val status: Status
    ) : CommonDefinition
}
