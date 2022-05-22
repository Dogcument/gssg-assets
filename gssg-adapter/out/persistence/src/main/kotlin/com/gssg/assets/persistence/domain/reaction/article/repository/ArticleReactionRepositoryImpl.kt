package com.gssg.assets.persistence.domain.reaction.article.repository

import com.gssg.assets.persistence.common.CommonRepository
import com.gssg.assets.persistence.domain.article.entity.ArticleEntities
import com.gssg.assets.persistence.domain.member.entity.MemberEntities
import com.gssg.assets.persistence.domain.reaction.article.entity.ArticleReactionEntities
import com.gssg.assets.persistence.domain.reaction.article.entity.ArticleReactionEntity
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * @Author Heli
 */
class ArticleReactionRepositoryImpl : ArticleReactionRepository,
    CommonRepository<Long, ArticleReactionEntities>(ArticleReactionEntities) {

    override fun insert(definition: ArticleReactionRepository.ArticleReactionDefinition) {
        execInsert {
            insertOrUpdate(it, definition)
        }
    }

    override fun update(id: Long, definition: ArticleReactionRepository.ArticleReactionDefinition) {
        execUpdate(id = id) {
            insertOrUpdate(it, definition)
        }
    }

    override fun findById(id: Long): ArticleReactionEntity? {
        return queryById(id = id) {
            ArticleReactionEntity.wrapRow(it)
        }
    }

    private fun ArticleReactionEntities.insertOrUpdate(
        it: UpdateBuilder<Number>,
        definition: ArticleReactionRepository.ArticleReactionDefinition
    ) {
        it[reactorId] = definition.reactorId
        it[targetId] = definition.targetId
        it[status] = definition.status.name
    }

    override fun findByTarget(targetId: Long): List<ArticleReactionEntity> {
        return ArticleReactionEntities.innerJoin(MemberEntities).innerJoin(ArticleEntities)
            .select { ArticleReactionEntities.targetId eq targetId }
            .map { ArticleReactionEntity.wrapRow(it) }
    }
}
