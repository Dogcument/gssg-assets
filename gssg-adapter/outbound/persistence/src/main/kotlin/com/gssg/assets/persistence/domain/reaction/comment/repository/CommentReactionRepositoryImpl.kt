package com.gssg.assets.persistence.domain.reaction.comment.repository

import com.gssg.assets.persistence.common.CommonRepository
import com.gssg.assets.persistence.domain.article.entity.ArticleEntities
import com.gssg.assets.persistence.domain.member.entity.MemberEntities
import com.gssg.assets.persistence.domain.reaction.comment.entity.CommentReactionEntities
import com.gssg.assets.persistence.domain.reaction.comment.entity.CommentReactionEntity
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * @Author Heli
 */
class CommentReactionRepositoryImpl : CommentReactionRepository,
    CommonRepository<Long, CommentReactionEntities>(CommentReactionEntities) {

    override fun insert(definition: CommentReactionRepository.CommentReactionDefinition) {
        execInsert {
            insertOrUpdate(it, definition)
        }
    }

    override fun update(id: Long, definition: CommentReactionRepository.CommentReactionDefinition) {
        execUpdate(id = id) {
            insertOrUpdate(it, definition)
        }
    }

    override fun findById(id: Long): CommentReactionEntity? {
        return queryById(id = id) {
            CommentReactionEntity.wrapRow(it)
        }
    }

    private fun CommentReactionEntities.insertOrUpdate(
        it: UpdateBuilder<Number>,
        definition: CommentReactionRepository.CommentReactionDefinition
    ) {
        it[reactorId] = definition.reactorId
        it[targetId] = definition.targetId
        it[type] = definition.type.name
    }

    override fun findByTarget(targetId: Long): List<CommentReactionEntity> {
        return CommentReactionEntities.innerJoin(MemberEntities).innerJoin(ArticleEntities)
            .select { CommentReactionEntities.targetId eq targetId }
            .map { CommentReactionEntity.wrapRow(it) }
    }
}
