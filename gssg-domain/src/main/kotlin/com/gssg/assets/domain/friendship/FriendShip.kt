package com.gssg.assets.domain.friendship

import com.gssg.assets.domain.BaseDomain
import com.gssg.assets.domain.friendship.enums.Status
import com.gssg.assets.domain.friendship.enums.Type
import java.time.LocalDateTime

/**
 * @Author Heli
 */
class FriendShip(
    override val id: FriendShipId = FriendShipId(-1L),
    val createdAt: FriendShipCreatedAt = FriendShipCreatedAt(LocalDateTime.MIN),
    val modifiedAt: FriendShipModifiedAt = FriendShipModifiedAt(LocalDateTime.MIN),
    val type: FriendShipType,
    val status: FriendShipStatus,
    val fromMemberId: FriendShipFromMemberId,
    val toMemberId: FriendShipToMemberId
) : BaseDomain() {

    companion object {

        fun follow(
            fromMemberId: FriendShipFromMemberId,
            toMemberId: FriendShipToMemberId
        ) = FriendShip(
            fromMemberId = fromMemberId,
            toMemberId = toMemberId,
            type = FriendShipType(type = Type.FOLLOW),
            status = FriendShipStatus(status = Status.ACTIVE)
        )
    }

    fun unfollow() = FriendShip(
        id = id,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        fromMemberId = fromMemberId,
        toMemberId = toMemberId,
        type = type,
        status = FriendShipStatus(status = Status.INACTIVE)
    )
}
