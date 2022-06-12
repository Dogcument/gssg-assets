package com.gssg.assets.domain.friendship

import com.gssg.assets.domain.BaseDomain
import com.gssg.assets.domain.friendship.enums.Status
import com.gssg.assets.domain.friendship.enums.Type
import java.time.LocalDateTime

/**
 * @Author Heli
 */
class Friendship(
    override val id: FriendshipId = FriendshipId(-1L),
    val createdAt: FriendshipCreatedAt = FriendshipCreatedAt(LocalDateTime.MIN),
    val modifiedAt: FriendshipModifiedAt = FriendshipModifiedAt(LocalDateTime.MIN),
    val type: FriendshipType,
    val status: FriendshipStatus,
    val fromMemberId: FriendshipFromMemberId,
    val toMemberId: FriendshipToMemberId
) : BaseDomain() {

    companion object {

        fun follow(
            fromMemberId: FriendshipFromMemberId,
            toMemberId: FriendshipToMemberId
        ) = Friendship(
            fromMemberId = fromMemberId,
            toMemberId = toMemberId,
            type = FriendshipType(type = Type.FOLLOW),
            status = FriendshipStatus(status = Status.ACTIVE)
        )
    }

    fun unfollow() = Friendship(
        id = id,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        fromMemberId = fromMemberId,
        toMemberId = toMemberId,
        type = type,
        status = FriendshipStatus(status = Status.INACTIVE)
    )
}
