package com.gssg.assets.application.domain.reaction.comment.port.out

import com.gssg.assets.domain.comment.CommentId
import com.gssg.assets.domain.reaction.comment.CommentReaction

/**
 * @Author Heli
 */
interface CommentReactionPersistencePort {

    fun insert(commentReaction: CommentReaction)

    fun update(commentReaction: CommentReaction)

    fun findBy(commentId: CommentId): List<CommentReaction>
}
