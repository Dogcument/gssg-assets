package com.gssg.assets.persistence.domain.reaction.comment.repository

import com.gssg.assets.domain.reaction.enums.ReactionType
import com.gssg.assets.persistence.domain.reaction.comment.entity.CommentReactionEntity

/**
 * @Author Heli
 */
interface CommentReactionRepository {

    fun insert(definition: CommentReactionDefinition)

    fun update(id: Long, definition: CommentReactionDefinition)

    fun findById(id: Long): CommentReactionEntity?

    fun findByTarget(targetId: Long): List<CommentReactionEntity>

    data class CommentReactionDefinition(
        val reactorId: Long,
        val targetId: Long,
        val type: ReactionType
    )
}
