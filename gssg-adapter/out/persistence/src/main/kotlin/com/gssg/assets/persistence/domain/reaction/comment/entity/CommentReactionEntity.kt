package com.gssg.assets.persistence.domain.reaction.comment.entity

import com.gssg.assets.persistence.domain.comment.entity.CommentEntity
import com.gssg.assets.persistence.domain.member.entity.MemberEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * @Author Heli
 */
class CommentReactionEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<CommentReactionEntity>(CommentReactionEntities)

    val createdAt by CommentReactionEntities.createdAt
    val modifiedAt by CommentReactionEntities.modifiedAt
    val reactor by MemberEntity referencedOn CommentReactionEntities.reactorId
    val target by CommentEntity referencedOn CommentReactionEntities.targetId
    val type by CommentReactionEntities.type
}
