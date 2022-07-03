package com.gssg.assets.persistence.domain.friendship.adapter.mapper

import com.gssg.assets.domain.friendship.*
import com.gssg.assets.domain.friendship.enums.Status
import com.gssg.assets.domain.friendship.enums.Type
import com.gssg.assets.domain.logger
import com.gssg.assets.persistence.domain.friendship.entity.FriendshipEntity
import com.gssg.assets.persistence.domain.friendship.repository.FriendshipRepository

/**
 * @Author Heli
 */
object FriendshipMapper {

    private val logger = logger()

    fun toDefinition(friendship: Friendship): FriendshipRepository.FriendShipDefinition {
        logger.info("어댑터 모듈의 매퍼에서 Friendship 객체를 엔티티 정의로 변경")
        return FriendshipRepository.FriendShipDefinition(
            type = friendship.type.type,
            status = friendship.status.status,
            fromMemberId = friendship.fromMemberId.fromMemberId,
            toMemberId = friendship.toMemberId.toMemberId
        )
    }

    fun toApplication(friendshipEntity: FriendshipEntity): Friendship {
        logger.info("어댑터 모듈의 매퍼에서 데이터베이스 엔티티를 Friendship 객체로 변경")
        return Friendship(
            id = FriendshipId(id = friendshipEntity.id.value),
            createdAt = FriendshipCreatedAt(createdAt = friendshipEntity.createdAt),
            modifiedAt = FriendshipModifiedAt(modifiedAt = friendshipEntity.modifiedAt),
            type = FriendshipType(type = Type.of(friendshipEntity.type)),
            status = FriendshipStatus(status = Status.of(friendshipEntity.status)),
            fromMemberId = FriendshipFromMemberId(fromMemberId = friendshipEntity.fromMember.id.value),
            toMemberId = FriendshipToMemberId(toMemberId = friendshipEntity.toMember.id.value)
        )
    }
}
