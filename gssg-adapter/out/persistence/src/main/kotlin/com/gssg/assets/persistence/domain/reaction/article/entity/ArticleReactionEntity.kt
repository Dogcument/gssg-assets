package com.gssg.assets.persistence.domain.reaction.article.entity

import com.gssg.assets.persistence.domain.article.entity.ArticleEntity
import com.gssg.assets.persistence.domain.member.entity.MemberEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * @Author Heli
 */
class ArticleReactionEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ArticleReactionEntity>(ArticleReactionEntities)

    val createdAt by ArticleReactionEntities.createdAt
    val modifiedAt by ArticleReactionEntities.modifiedAt
    val reactor by MemberEntity referencedOn ArticleReactionEntities.reactorId
    val target by ArticleEntity referencedOn ArticleReactionEntities.targetId
    val type by ArticleReactionEntities.type
}
