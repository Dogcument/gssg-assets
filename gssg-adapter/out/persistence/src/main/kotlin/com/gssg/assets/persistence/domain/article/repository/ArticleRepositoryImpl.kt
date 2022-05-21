package com.gssg.assets.persistence.domain.article.repository

import com.gssg.assets.persistence.common.CommonRepository
import com.gssg.assets.persistence.domain.article.entity.ArticleEntities
import com.gssg.assets.persistence.domain.article.entity.ArticleEntity
import com.gssg.assets.persistence.domain.topic.pick.entity.PickEntities
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import java.time.LocalDate

/**
 * @Author Heli
 */
class ArticleRepositoryImpl : ArticleRepository,
    CommonRepository<Long, ArticleEntities>(ArticleEntities) {

    override fun insert(definition: ArticleRepository.ArticleDefinition) {
        execInsert {
            insertOrUpdate(it, definition)
        }
    }

    override fun update(id: Long, definition: ArticleRepository.ArticleDefinition) {
        execUpdate(id = id) {
            insertOrUpdate(it, definition)
        }
    }

    private fun ArticleEntities.insertOrUpdate(
        it: UpdateBuilder<Number>,
        definition: ArticleRepository.ArticleDefinition
    ) {
        it[title] = definition.title
        it[content] = definition.content
        it[authorId] = definition.authorId
        it[pickId] = definition.pickId
        it[status] = definition.status.name
    }

    override fun findById(id: Long): ArticleEntity? {
        return queryById(id = id) {
            ArticleEntity.wrapRow(it)
        }
    }

    override fun findByPickTargetDate(pickTargetDate: LocalDate): List<ArticleEntity> {
        return ArticleEntities.innerJoin(PickEntities)
            .select { PickEntities.targetDate eq pickTargetDate }
            .map { ArticleEntity.wrapRow(it) }
    }
}
