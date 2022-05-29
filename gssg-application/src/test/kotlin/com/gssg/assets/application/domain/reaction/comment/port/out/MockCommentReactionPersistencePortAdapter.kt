package com.gssg.assets.application.domain.reaction.comment.port.out

import com.gssg.assets.domain.comment.CommentId
import com.gssg.assets.domain.reaction.comment.*
import com.gssg.assets.domain.reaction.enums.ReactionType
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicLong

/**
 * @Author Heli
 */
class MockCommentReactionPersistencePortAdapter : CommentReactionPersistencePort {

    private val db = mutableMapOf<CommentReactionId, CommentReaction>()
    private val distributedId = AtomicLong(1L)

    override fun insert(commentReaction: CommentReaction) {
        val commentReactionId = CommentReactionId(id = distributedId.getAndIncrement())
        db[commentReactionId] = commentReaction
    }

    override fun update(commentReaction: CommentReaction) {
        val commentReactionId = CommentReactionId(id = commentReaction.longId)
        db[commentReactionId] = commentReaction
    }

    override fun findBy(commentReactionId: CommentReactionId): CommentReaction? {
        return db[commentReactionId]
    }

    override fun findBy(commentId: CommentId): List<CommentReaction> {
        return db.values.filter {
            it.targetId.targetId == commentId.id
        }
    }

    fun initData() {
        val now = LocalDateTime.now()
        db[CommentReactionId(id = 1L)] = CommentReaction(
            id = CommentReactionId(id = 1L),
            createdAt = CommentReactionCreatedAt(createdAt = now),
            modifiedAt = CommentReactionModifiedAt(modifiedAt = now),
            reactorId = CommentReactionReactorId(reactorId = 1L),
            targetId = CommentReactionTargetId(targetId = 1L),
            type = CommentReactionType(type = ReactionType.LIKE)
        )
        db[CommentReactionId(id = 2L)] = CommentReaction(
            id = CommentReactionId(id = 2L),
            createdAt = CommentReactionCreatedAt(createdAt = now),
            modifiedAt = CommentReactionModifiedAt(modifiedAt = now),
            reactorId = CommentReactionReactorId(reactorId = 1L),
            targetId = CommentReactionTargetId(targetId = 1L),
            type = CommentReactionType(type = ReactionType.LIKE)
        )
        db[CommentReactionId(id = 3L)] = CommentReaction(
            id = CommentReactionId(id = 3L),
            createdAt = CommentReactionCreatedAt(createdAt = now),
            modifiedAt = CommentReactionModifiedAt(modifiedAt = now),
            reactorId = CommentReactionReactorId(reactorId = 1L),
            targetId = CommentReactionTargetId(targetId = 1L),
            type = CommentReactionType(type = ReactionType.LIKE)
        )
    }

    fun clear() {
        db.clear()
    }
}
