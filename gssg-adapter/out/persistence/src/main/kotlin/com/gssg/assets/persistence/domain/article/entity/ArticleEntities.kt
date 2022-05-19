package com.gssg.assets.persistence.domain.article.entity

import com.gssg.assets.persistence.common.CommonLongIdEntityTable
import com.gssg.assets.persistence.domain.member.entity.MemberEntities

/**
 * @Author Heli
 */
object ArticleEntities : CommonLongIdEntityTable(name = "article") {
    val title = varchar("title", 256)
    val content = text("content")
    val authorId = reference("authorId", MemberEntities)
    val status = varchar("status", 32)
}
