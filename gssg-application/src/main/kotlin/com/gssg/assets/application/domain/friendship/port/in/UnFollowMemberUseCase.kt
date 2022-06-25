package com.gssg.assets.application.domain.friendship.port.`in`

import com.gssg.assets.domain.friendship.FriendshipId

/**
 * @Author Heli
 */
interface UnFollowMemberUseCase {

    fun command(command: Command)

    data class Command(
        val friendshipId: FriendshipId
    )
}
