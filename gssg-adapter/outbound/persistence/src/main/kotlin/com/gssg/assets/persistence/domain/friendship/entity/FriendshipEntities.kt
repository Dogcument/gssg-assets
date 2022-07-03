package com.gssg.assets.persistence.domain.friendship.entity

import com.gssg.assets.persistence.common.CommonLongIdEntityTable
import com.gssg.assets.persistence.domain.member.entity.MemberEntities

/**
 * @Author Heli
 */
object FriendshipEntities : CommonLongIdEntityTable(name = "friend_ship") {
    val type = text("type")
    val status = text("status")
    val fromMemberId = reference("fromMemberId", MemberEntities)
    val toMemberId = reference("toMemberId", MemberEntities)
}
