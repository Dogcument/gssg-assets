package com.gssg.assets.application.domain.comment.port.out

import com.gssg.assets.domain.article.ArticleId
import com.gssg.assets.domain.comment.*
import com.gssg.assets.domain.comment.enums.Status
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicLong

/**
 * @Author Heli
 */
class MockCommentPersistencePortAdapter : CommentPersistencePort {

    private val db = mutableMapOf<CommentId, Comment>()
    private val distributedId = AtomicLong(1L)

    override fun insert(comment: Comment) {
        val commentId = CommentId(id = distributedId.getAndIncrement())
        db[commentId] = comment
    }

    override fun update(comment: Comment) {
        val commentId = CommentId(id = comment.longId)
        db[commentId] = comment
    }

    override fun findByArticleId(articleId: ArticleId): List<Comment> {
        return db.values.filter {
            it.articleId.id == articleId.id
        }
    }

    fun initData() {
        val now = LocalDateTime.now()
        (1L..3L).forEach { longId ->
            db[CommentId(id = longId)] = Comment(
                id = CommentId(id = longId),
                createdAt = CommentCreatedAt(createdAt = now),
                modifiedAt = CommentModifiedAt(modifiedAt = now),
                content = CommentContent(content = "Hi i am Heli"),
                authorId = CommentAuthorId(id = 1L),
                articleId = CommentArticleId(id = 1L),
                status = CommentStatus(status = Status.ACTIVE)
            )
        }
    }

    fun clear() {
        db.clear()
    }
}
