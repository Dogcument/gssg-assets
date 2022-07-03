package com.gssg.assets.persistence.domain.comment.repository

import com.gssg.assets.persistence.common.CommonRepository
import com.gssg.assets.persistence.domain.comment.entity.CommentEntities
import com.gssg.assets.persistence.domain.comment.entity.CommentEntity
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.UpdateBuilder

/**
 * @Author Heli
 */
class CommentRepositoryImpl : CommentRepository,
    CommonRepository<Long, CommentEntities>(CommentEntities) {

    override fun insert(definition: CommentRepository.CommentDefinition) {
        execInsert {
            insertOrUpdate(it, definition)
        }
    }

    override fun update(id: Long, definition: CommentRepository.CommentDefinition) {
        execUpdate(id = id) {
            insertOrUpdate(it, definition)
        }
    }

    override fun findById(id: Long): CommentEntity? {
        return queryById(id = id) {
            CommentEntity.wrapRow(it)
        }
    }

    private fun CommentEntities.insertOrUpdate(
        it: UpdateBuilder<Number>,
        definition: CommentRepository.CommentDefinition
    ) {
        it[content] = definition.content
        it[authorId] = definition.authorId
        it[articleId] = definition.articleId
        it[status] = definition.status.name
    }

    override fun findByArticleId(articleId: Long): List<CommentEntity> {
//        return CommentEntities.innerJoin(MemberEntities).innerJoin(ArticleEntities)
        return CommentEntities
            .select { CommentEntities.articleId eq articleId }
            .map { CommentEntity.wrapRow(it) }
    }
}
