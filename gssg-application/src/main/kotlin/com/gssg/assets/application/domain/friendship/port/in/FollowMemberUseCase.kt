package com.gssg.assets.application.domain.friendship.port.`in`

import com.gssg.assets.domain.friendship.Friendship

/**
 * @Author Heli
 */
interface FollowMemberUseCase {

    fun command(command: Command)

    data class Command(
        val friendship: Friendship
    )
}
