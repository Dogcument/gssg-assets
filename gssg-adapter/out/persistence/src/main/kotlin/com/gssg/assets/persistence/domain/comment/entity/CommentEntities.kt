package com.gssg.assets.persistence.domain.comment.entity

import com.gssg.assets.persistence.common.CommonLongIdEntityTable
import com.gssg.assets.persistence.domain.article.entity.ArticleEntities
import com.gssg.assets.persistence.domain.member.entity.MemberEntities

/**
 * @Author Heli
 */
object CommentEntities : CommonLongIdEntityTable(name = "comment") {
    val content = text("content")
    val authorId = reference("authorId", MemberEntities)
    val articleId = reference("articleId", ArticleEntities)
    val status = varchar("status", 32)
}
