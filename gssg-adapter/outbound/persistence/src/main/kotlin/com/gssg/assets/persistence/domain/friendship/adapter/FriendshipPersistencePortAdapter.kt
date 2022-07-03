package com.gssg.assets.persistence.domain.friendship.adapter

import com.gssg.assets.application.domain.friendship.port.out.FriendshipPersistencePort
import com.gssg.assets.domain.friendship.Friendship
import com.gssg.assets.domain.friendship.FriendshipFromMemberId
import com.gssg.assets.persistence.domain.friendship.adapter.mapper.FriendshipMapper
import com.gssg.assets.persistence.domain.friendship.repository.FriendshipRepository

/**
 * @Author Heli
 */
class FriendshipPersistencePortAdapter(
    private val friendshipRepository: FriendshipRepository
) : FriendshipPersistencePort {

    override fun insert(friendship: Friendship) {
        val definition = FriendshipMapper.toDefinition(
            friendship = friendship
        )
        friendshipRepository.insert(definition = definition)
    }

    override fun update(friendship: Friendship) {
        val definition = FriendshipMapper.toDefinition(
            friendship = friendship
        )
        friendshipRepository.update(id = friendship.longId, definition = definition)
    }

    override fun findByFromMemberId(fromMemberId: FriendshipFromMemberId): List<Friendship> {
        val friendshipEntities = friendshipRepository.findByFromMemberId(
            fromMemberId = fromMemberId.fromMemberId
        )
        return friendshipEntities.map {
            FriendshipMapper.toApplication(friendshipEntity = it)
        }
    }
}
