package com.gssg.assets.application.domain.friendship.service.exception

import com.gssg.assets.domain.friendship.FriendshipId

/**
 * @Author Heli
 */
class FriendshipNotFoundException(
    override val message: String?
) : RuntimeException() {
    constructor(friendshipId: FriendshipId) : this("Friendship 을 찾지 못했습니다 [Friendship.id=${friendshipId.id}]")
}
