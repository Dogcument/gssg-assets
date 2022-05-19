package com.gssg.assets.persistence.domain.article.entity

import com.gssg.assets.persistence.domain.member.entity.MemberEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * @Author Heli
 */
class ArticleEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ArticleEntity>(ArticleEntities)

    val createdAt by ArticleEntities.createdAt
    val modifiedAt by ArticleEntities.modifiedAt
    val title by ArticleEntities.title
    val content by ArticleEntities.content
    val author by MemberEntity referencedOn ArticleEntities.authorId
    val status by ArticleEntities.status
}
