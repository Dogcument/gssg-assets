package com.gssg.assets.persistence.domain.reaction.article.entity

import com.gssg.assets.persistence.common.CommonLongIdEntityTable
import com.gssg.assets.persistence.domain.article.entity.ArticleEntities
import com.gssg.assets.persistence.domain.member.entity.MemberEntities

/**
 * @Author Heli
 */
object ArticleReactionEntities : CommonLongIdEntityTable(name = "article_reaction") {
    val reactorId = reference("reactorId", MemberEntities)
    val targetId = reference("targetId", ArticleEntities)
    val type = varchar("type", 32)
}
