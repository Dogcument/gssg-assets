package com.gssg.assets.domain.comment

import com.gssg.assets.domain.BaseDomain
import com.gssg.assets.domain.comment.enums.Status
import java.time.LocalDateTime

/**
 * @Author Heli
 */
class Comment(
    override val id: CommentId = CommentId(-1L),
    val createdAt: CommentCreatedAt = CommentCreatedAt(LocalDateTime.MIN),
    val modifiedAt: CommentModifiedAt = CommentModifiedAt(LocalDateTime.MIN),
    val content: CommentContent,
    val authorId: CommentAuthorId,
    val articleId: CommentArticleId,
    val status: CommentStatus
) : BaseDomain() {

    companion object {
        fun create(
            newContent: CommentContent,
            authorId: CommentAuthorId,
            articleId: CommentArticleId
        ) = Comment(
            content = newContent,
            authorId = authorId,
            articleId = articleId,
            status = CommentStatus(status = Status.ACTIVE)
        )
    }

    fun update(
        newContent: CommentContent?
    ): Comment {
        val content = newContent ?: content
        return Comment(
            id = id,
            createdAt = createdAt,
            modifiedAt = modifiedAt,
            content = content,
            authorId = authorId,
            articleId = articleId,
            status = status
        )
    }

    fun delete() = Comment(
        id = id,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        content = content,
        authorId = authorId,
        articleId = articleId,
        status = CommentStatus(status = Status.DELETED)
    )
}
