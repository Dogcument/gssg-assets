package com.gssg.assets.persistence.domain.comment.entity

import com.gssg.assets.persistence.domain.article.entity.ArticleEntity
import com.gssg.assets.persistence.domain.member.entity.MemberEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * @Author Heli
 */
class CommentEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<CommentEntity>(CommentEntities)

    val createdAt by CommentEntities.createdAt
    val modifiedAt by CommentEntities.modifiedAt
    val content by CommentEntities.content
    val author by MemberEntity referencedOn CommentEntities.authorId
    val article by ArticleEntity referencedOn CommentEntities.articleId
    val status by CommentEntities.status
}
