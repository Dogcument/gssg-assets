package com.gssg.assets.application.domain.friendship.port.out

import com.gssg.assets.domain.friendship.Friendship
import com.gssg.assets.domain.friendship.FriendshipFromMemberId
import com.gssg.assets.domain.friendship.FriendshipId

/**
 * @Author Heli
 */
interface FriendshipPersistencePort {

    fun insert(friendship: Friendship)

    fun update(friendship: Friendship)

    fun findBy(id: FriendshipId): Friendship?
    fun findBy(fromMemberId: FriendshipFromMemberId): List<Friendship>
}
