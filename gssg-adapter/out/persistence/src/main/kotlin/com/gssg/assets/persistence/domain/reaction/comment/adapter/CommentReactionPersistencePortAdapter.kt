package com.gssg.assets.persistence.domain.reaction.comment.adapter

import com.gssg.assets.application.domain.reaction.comment.port.out.CommentReactionPersistencePort
import com.gssg.assets.domain.comment.CommentId
import com.gssg.assets.domain.reaction.comment.CommentReaction
import com.gssg.assets.persistence.domain.reaction.comment.adapter.mapper.CommentReactionMapper
import com.gssg.assets.persistence.domain.reaction.comment.repository.CommentReactionRepository

/**
 * @Author Heli
 */
class CommentReactionPersistencePortAdapter(
    private val commentReactionRepository: CommentReactionRepository
) : CommentReactionPersistencePort {

    override fun insert(commentReaction: CommentReaction) {
        val definition = CommentReactionMapper.toDefinition(commentReaction = commentReaction)
        commentReactionRepository.insert(definition = definition)
    }

    override fun update(commentReaction: CommentReaction) {
        val definition = CommentReactionMapper.toDefinition(commentReaction = commentReaction)
        commentReactionRepository.update(id = commentReaction.longId, definition = definition)
    }

    override fun findBy(commentId: CommentId): List<CommentReaction> {
        val commentReactionEntities = commentReactionRepository.findByTarget(
            targetId = commentId.id
        )
        return commentReactionEntities.map {
            CommentReactionMapper.toApplication(commentReactionEntity = it)
        }
    }
}
