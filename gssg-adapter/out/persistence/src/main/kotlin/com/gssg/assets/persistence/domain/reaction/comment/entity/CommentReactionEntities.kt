package com.gssg.assets.persistence.domain.reaction.comment.entity

import com.gssg.assets.persistence.common.CommonLongIdEntityTable
import com.gssg.assets.persistence.domain.comment.entity.CommentEntities
import com.gssg.assets.persistence.domain.member.entity.MemberEntities

/**
 * @Author Heli
 */
object CommentReactionEntities : CommonLongIdEntityTable(name = "comment_reaction") {
    val reactorId = reference("reactorId", MemberEntities)
    val targetId = reference("targetId", CommentEntities)
    val type = varchar("type", 32)
}
