package com.gssg.assets.application.domain.reaction.comment.port.out

import com.gssg.assets.domain.comment.Comment
import com.gssg.assets.domain.reaction.comment.CommentReaction

/**
 * @Author Heli
 */
interface CommentReactionPersistencePort {

    fun insert(commentReaction: CommentReaction)

    fun update(commentReaction: CommentReaction)

    fun findBy(target: Comment): List<CommentReaction>
}
