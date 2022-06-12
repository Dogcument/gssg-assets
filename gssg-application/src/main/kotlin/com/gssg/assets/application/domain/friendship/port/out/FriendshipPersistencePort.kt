package com.gssg.assets.application.domain.friendship.port.out

import com.gssg.assets.domain.friendship.Friendship
import com.gssg.assets.domain.friendship.FriendshipFromMemberId

/**
 * @Author Heli
 */
interface FriendshipPersistencePort {

    fun insert(friendship: Friendship)

    fun update(friendship: Friendship)

    fun findByFromMemberId(fromMemberId: FriendshipFromMemberId): List<Friendship>
}
