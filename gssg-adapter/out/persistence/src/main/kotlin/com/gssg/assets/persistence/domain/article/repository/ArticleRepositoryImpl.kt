package com.gssg.assets.persistence.domain.article.repository

import com.gssg.assets.persistence.common.CommonRepository
import com.gssg.assets.persistence.domain.article.entity.ArticleEntities
import com.gssg.assets.persistence.domain.article.entity.ArticleEntity
import org.jetbrains.exposed.sql.statements.UpdateBuilder

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
        it[status] = definition.status.name
    }

    override fun findById(id: Long): ArticleEntity? {
        return queryById(id = id) {
            ArticleEntity.wrapRow(it)
        }
    }
}
