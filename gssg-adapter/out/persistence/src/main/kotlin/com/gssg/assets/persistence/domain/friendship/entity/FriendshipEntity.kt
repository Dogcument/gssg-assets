package com.gssg.assets.persistence.domain.friendship.entity

import com.gssg.assets.persistence.domain.member.entity.MemberEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * @Author Heli
 */
class FriendshipEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<FriendshipEntity>(FriendshipEntities)

    val createdAt by FriendshipEntities.createdAt
    val modifiedAt by FriendshipEntities.modifiedAt
    val type by FriendshipEntities.type
    val status by FriendshipEntities.status
    val fromMember by MemberEntity referencedOn FriendshipEntities.fromMemberId
    val toMember by MemberEntity referencedOn FriendshipEntities.toMemberId
}
